package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.client.console.utils.UserInputUtil
import lab7.shared.entities.organization.Organization
import lab7.client.collection.CollectionManager
import java.io.BufferedReader

/**
 * Класс исполнителя команды remove_lower.
 * @constructor repo - Репозиторий выполнения команды.
 * @constructor reader - Пользовательский ввод
 */
class RemoveLowerExecutor(
    private val repo: CollectionManager<Organization>,
    private val reader: BufferedReader
): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo.removeLowerThen(UserInputUtil.readOrganizationInput(reader)))
            println("Удалены все меньшие элементы")
        else
            println("Не удалось удалить все меньшие элементы")
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "remove_lower - удалить из коллекции все элементы, меньшие, чем заданный"
    }
}