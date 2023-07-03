package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.client.console.utils.UserInputUtil
import lab6.shared.entities.organization.Organization
import java.io.BufferedReader

/**
 * Класс исполнителя команды add.
 * @constructor repo - Репозиторий для добавления элемента.
 * @constructor reader - Пользовательский ввод
 */
class AddExecutor(
    private val reader: BufferedReader,
    private val add: (Organization) -> Boolean
): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (add(UserInputUtil.readOrganizationInput(reader)))
            println("Элемент успешно добавлен в коллекцию")
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