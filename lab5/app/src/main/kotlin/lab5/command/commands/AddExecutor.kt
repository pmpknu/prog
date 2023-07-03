package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.utils.UserInputUtil
import lab5.entities.organization.Organization
import java.io.BufferedReader

/**
 * Класс исполнителя команды add.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class AddExecutor<T>(
    private val reader: BufferedReader,
    private val addfun: (T) -> Boolean
): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (addfun(UserInputUtil.readOrganizationInput(reader) as T))
            println("Элемент учпешно добавлен в коллекцию")
        else
            println("Не удалось добавить элемент в коллекцию")
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "add - добавить новый элемент в коллекцию\n" +
                "add_if_max - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min - добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции"
    }
}