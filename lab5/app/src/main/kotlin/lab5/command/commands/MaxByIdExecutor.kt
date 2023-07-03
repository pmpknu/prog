package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.utils.UserInputUtil
import java.io.BufferedReader

/**
 * Класс исполнителя команды max_by_id.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class MaxByIdExecutor<T>(private val repo: CollectionManager<T>): Executor {
    /**
     * Обновляет элемент по указанноу ID.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }

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