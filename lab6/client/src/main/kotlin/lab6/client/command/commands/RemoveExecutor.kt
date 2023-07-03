package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

/**
 * Класс исполнителя команды remove.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class RemoveExecutor(private val repo: CollectionManager<Organization>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        val id = try {
            arg.toInt()
        } catch (e: Exception) {
            println("ID должен быть натуральным. Err: $e")
            return
        }

        repo.removeElementByID(id=id)
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "remove <id> - удалить элемент из коллекции по его id"
    }
}