package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.client.console.ConsoleHandler
import lab7.shared.entities.organization.Organization
import java.io.*
import java.util.*
import lab7.client.collection.CollectionManager

/**
 * Класс исполнителя команды execute_script.
 * @constructor repo - Репозиторий для исполнения скрипта.
 */
class ScriptExecutor(private val repo: CollectionManager<Organization>): Executor {
    /**
     * Выводит список команд.
     * @param arg Название исполняемого файла.
     */
    override fun execute(arg: String) {
        val file = File(arg.trim())
        val reader : BufferedReader

        if (file.exists() && file.isFile) {
            try {
                if (!file.canRead())
                    println("Нет прав на чтение из файла")

                reader = BufferedReader(FileReader(file))
            } catch (e: Exception) {
                when (e) {
                    is SecurityException -> println("Нет прав на чтение из файла. Err: $e")
                    is FileNotFoundException -> println("Файл не найден. Err: $e")
                    is IOException -> println("Возникла ошибка при чтении из файла. Err: $e")
                    else -> println("Неопознанная ошибка при чтении из файла. Err: $e")
                }
                return
            }
        } else {
            println("Указанный файл не существует")
            return
        }

        if (file in stack) {
            println("Вероятна рекурсия. Выполение команд из файла преостановлено.")
            return
        }

        stack.push(file)

        ConsoleHandler(
            udp = this.repo,
            reader = reader
        ).also {
            it.loop()
        }

        stack.pop()
    }

    companion object {
        private val stack = Stack<File>()
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "execute_script <filename> - считать и исполнить скрипт из указанного файла"
    }
}