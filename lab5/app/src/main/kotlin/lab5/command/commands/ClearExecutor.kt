package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor

/**
 * Класс исполнителя команды clear.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class ClearExecutor<T>(private val repo: CollectionManager<T>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }

        (repo as OrganizationCollection).clear()
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "clear - очистить коллекцию"
    }
}