package lab6.server

import lab6.server.collection.filehandler.XmlHandler
import lab6.server.collection.organizationsCollections.OrganizationCollection
import lab6.server.communicator.DatagramChannelCommunicator
import lab6.server.console.ConsoleHandler
import lab6.server.handler.CommandsRequestHandler
import lab6.shared.Config
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import kotlin.concurrent.thread

fun main() {
    val port = Config.SERVER_PORT
    val filename = System.getenv("FILENAME")
    val file: File? = if (filename.isNullOrEmpty()) {
        println("Переменная окружения FILENAME не указана. Данные загружены не будут.\n")
        null
    } else {
        File(filename)
    }
    val fileHandler = XmlHandler(file)
    val organizationCollection = OrganizationCollection(fileHandler)
    Runtime.getRuntime().addShutdownHook(thread(start = false) {
        organizationCollection.saveToFile()
    })
    val handler = CommandsRequestHandler(organizationCollection)
    val channel = DatagramChannel.open()
    channel.configureBlocking(false)
    channel.bind(InetSocketAddress(port))
    val communicator = DatagramChannelCommunicator(channel, Config.CHUNK_SIZE)
    val server = Server(communicator, handler)
    thread {
        server.run()
    }

    val consoleHandeler = ConsoleHandler(organizationCollection, BufferedReader(InputStreamReader(System.`in`)))
    thread {
        consoleHandeler.loop()
    }

}

