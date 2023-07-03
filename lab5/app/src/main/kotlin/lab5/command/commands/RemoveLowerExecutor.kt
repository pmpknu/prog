package lab5.command.commands

import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.utils.UserInputUtil
import java.io.BufferedReader

/**
 * Класс исполнителя команды remove_lower.
 * @constructor repo - Репозиторий выполнения команды.
 * @constructor reader - Пользовательский ввод
 */
class RemoveLowerExecutor<T>(
    private val repo: T,
    private val reader: BufferedReader
): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }
        if ((repo as OrganizationCollection).removeLowerThen(UserInputUtil.readOrganizationInput(reader)))
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