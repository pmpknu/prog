package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.shared.entityCollection.CollectionInfo

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