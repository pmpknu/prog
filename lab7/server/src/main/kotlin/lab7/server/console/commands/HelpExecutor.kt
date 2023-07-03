package lab7.server.console.commands

import lab7.server.console.commands.Executor

/**
 * Класс исполнителя команды help.
 * @constructor Список команд.
 */
class HelpExecutor(private val getCommandsDescription: () -> Iterable<String>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        getCommandsDescription()
            .forEach { println(it) }
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "help - вывести справку по доступным командам"
    }
}