/*
 * Main file.
 */
package lab5

import lab5.collection.filehandler.XmlHandler
import lab5.collection.organizationsCollections.OrganizationCollection
import lab5.console.ConsoleHandler
import lab5.entities.organization.Organization
import lab5.entities.organization.OrganizationsSet
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * Главная функция. Точка входа в программу.
 */
fun main() {

    val filename = System.getenv("FILENAME")
    val file: File? = if (filename.isNullOrEmpty()) {
        println("Переменная окружения FILENAME не указана. Данные загружены не будут.\n")
        null
    } else {
        File(filename)
    }
    ConsoleHandler(
        OrganizationCollection(XmlHandler(file)),
        BufferedReader(InputStreamReader(System.`in`))
    ).also {
        it.loop()
    }
}
