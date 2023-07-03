package lab6.client.command.commands

import lab6.client.command.Executor
import lab6.client.console.utils.UserInputUtil
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager
import java.io.BufferedReader

/**
 * Класс исполнителя команды max_by_id.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class FilterGreaterExecutor(private val repo: CollectionManager<Organization>, private val reader: BufferedReader):
    Executor {
    /**
     * Обновляет элемент по указанноу ID.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        repo.filterGreaterThanPostalAddress(UserInputUtil.readAddressInput(reader)).forEach { println(it) }
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "filter_greater_than_postal_address <postalAddress> : вывести элементы, значение поля postalAddress которых больше заданного"
    }
}