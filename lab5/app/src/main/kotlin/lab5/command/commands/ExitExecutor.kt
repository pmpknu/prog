package lab5.command.commands

import lab5.command.Executor

/**
 * Класс исполнителя команды exit.
 * @constructor Выход из программы.
 */
class ExitExecutor(private val stopHandling: () -> Unit): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        stopHandling()
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "exit - завершить программу (без сохранения в файл)"
    }
}