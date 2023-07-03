package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor
import lab5.console.utils.UserInputUtil
import java.io.BufferedReader

/**
 * Класс исполнителя команды max_by_id.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class FilterGreaterExecutor<T>(private val repo: CollectionManager<T>, private val reader: BufferedReader): Executor {
    /**
     * Обновляет элемент по указанноу ID.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }


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