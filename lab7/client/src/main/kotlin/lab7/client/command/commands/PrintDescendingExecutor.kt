package lab7.client.command.commands

import lab7.client.command.Executor
import lab7.shared.entities.organization.Organization
import lab7.client.collection.CollectionManager

/**
 * Класс исполнителя команды print_descending.
 * @constructor repo - Репозиторий выполнения команды.
 */
class PrintDescendingExecutor(private val repo: CollectionManager<Organization>) : Executor {
    /**
     * Выводит организации в порядке убывания.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        repo.returnDescending().forEach { println(it) }
    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "print_descending - вывести элементы коллекции в порядке убывания"
    }
}