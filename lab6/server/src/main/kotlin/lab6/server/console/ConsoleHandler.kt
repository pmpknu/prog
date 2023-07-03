package lab6.server.console

import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager
import lab6.server.console.commands.Executor
import lab6.server.console.commands.HelpExecutor
import lab6.server.console.commands.SaveExecutor
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
        "save" to SaveExecutor(coll),
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