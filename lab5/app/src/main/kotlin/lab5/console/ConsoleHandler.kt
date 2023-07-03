package lab5.console

import lab5.collection.CollectionManager
import lab5.command.Executor
import lab5.command.commands.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

/**
 * Обработчик команд.
 * @param repo Коллекция организации, команды которой будут выполнятся.
 */
class ConsoleHandler<T>(private val repo: CollectionManager<T>, private val reader: BufferedReader) {
    private var isLoop: Boolean = true
    private val commands: Map<String, Executor> = mapOf(
        "help" to HelpExecutor { getCommandsDescription() },
        "exit" to ExitExecutor { isLoop = false },
        "info" to InfoExecutor { repo.info() },
        "show" to ShowExecutor { repo.show() },
        "add" to AddExecutor(reader, repo::add),
        "update" to UpdateExecutor(repo, reader),
        "remove" to RemoveExecutor(repo),
        "clear" to ClearExecutor(repo),
        "save" to SaveExecutor(repo),
        "execute_script" to ScriptExecutor(repo),
        "add_if_max" to AddExecutor(reader, repo::addIfMax),
        "add_if_min" to AddExecutor(reader, repo::addIfMin),
        "remove_lower" to RemoveLowerExecutor(repo, reader),
        "max_by_id" to MaxByIdExecutor(repo),
        "filter_greater_than_postal_address" to FilterGreaterExecutor(repo, reader),
        "print_descending" to PrintDescendingExecutor(repo)
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