package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.shared.entities.organization.Organization
import lab7.client.collection.CollectionManager

/**
 * Класс исполнителя команды clear.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class ClearExecutor(private val repo: CollectionManager<Organization>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        repo.clear()
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "clear - очистить коллекцию"
    }
}