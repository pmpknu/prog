package lab6.server.console.commands

import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionManager

/**
 * Класс исполнителя команды save.
 * @constructor repo - Репозиторий для добавления элемента.
 */
class SaveExecutor(private val repo: CollectionManager<Organization>): Executor {
    /**
     * Выводит список команд.
     * @param arg Не используется, нет аргументов.
     */
    override fun execute(arg: String) {
        if (repo.saveToFile())
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