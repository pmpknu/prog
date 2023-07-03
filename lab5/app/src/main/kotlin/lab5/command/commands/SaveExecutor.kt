package lab5.command.commands

import lab5.collection.CollectionManager
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.command.Executor

/**
 * Класс исполнителя команды save.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class SaveExecutor<T>(private val repo: CollectionManager<T>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo !is OrganizationCollection) {
            println("Can not add non-organization item")
            return
        }

        if ((repo as OrganizationCollection).saveToFile())
            println("Файл сохранен")
        else
            println("Не удается сохранить в файл")

    }

    /**
     * Переопределение метода преобразования к строке
     * @return Строка, описывающая класс.
     */
    override fun toString(): String {
        return "save - сохранить в файл"
    }
}