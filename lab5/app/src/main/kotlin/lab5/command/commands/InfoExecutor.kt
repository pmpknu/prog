package lab5.command.commands

import lab5.collection.CollectionInfo
import lab5.command.Executor

/**
 * Класс исполнителя команды info.
 * @constructor Информация о коллекции.
 */
class InfoExecutor(private val info: () -> CollectionInfo): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        println(info())
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "info - вывести в стандартный поток вывода информацию о коллекции"
    }
}