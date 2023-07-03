package lab6.client.command.commands

import lab6.client.command.Executor

/**
 * Класс исполнителя команды show.
 * @constructor Перечесляемый набор элементов коллекции.
 */
class ShowExecutor<T>(private val show: () -> Iterable<T>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        show().forEach { println("$it\n") }
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении"
    }
}