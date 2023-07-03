package lab7.client.console

import lab7.client.command.Executor
import lab7.client.command.commands.*
import lab7.shared.entities.organization.Organization
import lab7.client.collection.CollectionManager
import java.io.BufferedReader
import java.net.SocketTimeoutException
import java.util.regex.Pattern

/**
 * Обработчик команд.
 * @param udp Коллекция организации, команды которой будут выполнятся.
 */
class ConsoleHandler(private val udp: CollectionManager<Organization>, private val reader: BufferedReader) {
    private var isLoop: Boolean = true
    private val commands: Map<String, Executor> = mapOf(
        "help" to HelpExecutor { getCommandsDescription() },
        "exit" to ExitExecutor { isLoop = false },
        "info" to InfoExecutor { udp.info() },
        "show" to ShowExecutor { udp.show() },
        "add" to AddExecutor(reader, udp::add),
        "update" to UpdateExecutor(udp, reader),
        "remove" to RemoveExecutor(udp),
        "clear" to ClearExecutor(udp),
        "execute_script" to ScriptExecutor(udp),
        "add_if_max" to AddExecutor(reader, udp::addIfMax),
        "add_if_min" to AddExecutor(reader, udp::addIfMin),
        "remove_lower" to RemoveLowerExecutor(udp, reader),
        "max_by_id" to MaxByIdExecutor(udp),
        "filter_greater_than_postal_address" to FilterGreaterExecutor(udp, reader),
        "print_descending" to PrintDescendingExecutor(udp)
    )

    /**
     * Получить опиасние команд.
     * @return Список описаний команд.
     */
    private fun getCommandsDescription(): List<String> {
        return commands.map {
            "${it.component2()}"
        }
    }

    /**
     * Цикл обработки команд из входного потока.
     * Завершает обработку входного потока при изменении поля isLoop на false.
     */
    fun loop() {
        try {
            while (isLoop) {
                print(">")
                manager(reader.readLine())
            }
        } catch (e: Exception) {
            when(e) {
                is SocketTimeoutException -> println("Сервер недоступен.")

                else -> println("Непредвиденная ошибка чтения. Err: $e")
            }
            return
        }
    }

    /**
     * Управление вызовом и исполнением команд.
     * @param input Входной поток.
     */
    private fun manager(input: String) {
        val proc: List<String> = input.trim().split(Pattern.compile(" "), 2)

        if (proc[0] !in commands) {
            println("Нет такой команды")
            return
        }
        commands[proc[0]]?.execute(proc.getOrNull(1)?.trim() ?: "")!!
    }
}