package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

/**
 * Класс исполнителя команды max_by_id.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class MaxByIdExecutor(private val repo: CollectionManager<Organization>): Executor {
    /**
     * Обновляет элемент по указанноу ID.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        println(repo.returnMaxIdElement() ?: "В коллекции нет элемента с максимальным ID")
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "max_by_id - вывести любой объект из коллекции, значение поля id которого является максимальным"
    }
}
