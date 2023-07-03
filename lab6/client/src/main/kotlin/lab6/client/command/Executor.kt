package lab6.client.command

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