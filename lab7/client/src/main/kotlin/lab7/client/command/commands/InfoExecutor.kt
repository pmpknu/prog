package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.shared.entityCollection.CollectionInfo

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