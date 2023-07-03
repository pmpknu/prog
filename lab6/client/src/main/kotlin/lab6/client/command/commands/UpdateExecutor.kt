package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager
import java.io.BufferedReader

/**
 * Класс исполнителя команды update.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class UpdateExecutor(
    private val repo: CollectionManager<Organization>,
    private val reader: BufferedReader): Executor {
    /**
     * Обновляет элемент по указанноу ID.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        val id = try {
          arg.toInt()
        } catch (e: Exception) {
            println("ID должен быть натуральным. Err: $e")
            return
        }

        /*(repo as OrganizationCollection).updateElementByID(
            UserInputUtil.readOrganizationInput(reader).copy(id=id)
        )*/
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "update <id> - обновить значение элемента коллекции, id которого равен заданному"
    }
}