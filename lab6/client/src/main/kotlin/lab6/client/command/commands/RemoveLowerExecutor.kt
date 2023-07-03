package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.client.console.utils.UserInputUtil
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager
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