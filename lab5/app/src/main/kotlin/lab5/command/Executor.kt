package lab5.command

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