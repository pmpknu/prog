package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.filehandler.XmlHandler
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.ConsoleHandler
import java.io.*
import java.util.*

/**
 * Класс исполнителя команды execute_script.
 * @constructor repo - Репозиторий для исполнения скрипта.
 */
class ScriptExecutor<T>(private val repo: CollectionManager<T>): Executor {
    /**
     * Выводит список команд.
     * @param arg Название исполняемого файла.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }
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
            repo = this.repo,
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