package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.shared.entities.organization.Organization
import lab7.client.collection.CollectionManager

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
