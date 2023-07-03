package lab7.server.console.commands

/**
 * Интерфейс исполнителя
 */
interface Executor {
    /**
     * Команда исполнения.
     * @param arg Аргумент команды.
     */
    fun execute(arg: String)
}