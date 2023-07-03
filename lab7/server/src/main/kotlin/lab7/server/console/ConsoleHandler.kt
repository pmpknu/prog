package lab7.server.console

import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager
import lab7.server.console.commands.Executor
import lab7.server.console.commands.HelpExecutor
import java.io.BufferedReader
import java.util.regex.Pattern

/**
 * Обработчик команд.
 * @param coll Коллекция организации, команды которой будут выполнятся.
 */
class ConsoleHandler(private val coll: CollectionManager<Organization>, private val reader: BufferedReader) {
    private var isLoop: Boolean = true
    private val commands: Map<String, Executor> = mapOf(
        "help" to HelpExecutor { getCommandsDescription() },
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
            println("Непредвиденная ошибка чтения. Err: $e")
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