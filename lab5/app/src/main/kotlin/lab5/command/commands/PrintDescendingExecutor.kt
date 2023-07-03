package lab5.command.commands

import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor

/**
 * Класс исполнителя команды print_descending.
 * @constructor repo - Репозиторий выполнения команды.
 */
class PrintDescendingExecutor<T>(private val repo: T): Executor {
    /**
     * Выводит организации в порядке убывания.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }

        (repo as OrganizationCollection).returnDescending().forEach { println(it) }
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "print_descending - вывести элементы коллекции в порядке убывания"
    }
}