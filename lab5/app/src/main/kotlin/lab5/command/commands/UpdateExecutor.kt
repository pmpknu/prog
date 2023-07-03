package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.utils.UserInputUtil
import java.io.BufferedReader

/**
 * Класс исполнителя команды update.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class UpdateExecutor<T>(private val repo: CollectionManager<T>, private val reader: BufferedReader): Executor {
    /**
     * Обновляет элемент по указанноу ID.
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

        (repo as OrganizationCollection).updateElementByID(
            UserInputUtil.readOrganizationInput(reader).copy(id=id)
        )
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "update <id> - обновить значение элемента коллекции, id которого равен заданному"
    }
}