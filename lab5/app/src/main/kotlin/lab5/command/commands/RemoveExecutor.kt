package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor

/**
 * Класс исполнителя команды remove.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class RemoveExecutor<T>(private val repo: CollectionManager<T>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }

        val id = try {
            arg.toInt()
        } catch (e: Exception) {
            println("ID должен быть натуральным. Err: $e")
            return
        }

        (repo as OrganizationCollection).removeElementByID(id=id)
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "remove <id> - удалить элемент из коллекции по его id"
    }
}