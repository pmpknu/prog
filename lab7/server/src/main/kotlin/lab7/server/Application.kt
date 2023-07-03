package lab7.server

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import lab7.server.collection.databasehandler.PostgresHandler
import lab7.server.collection.databasehandler.dbuser.UserDatabaseManager
import lab7.server.collection.organizationsCollections.OrganizationCollection
import lab7.server.communicator.DatagramChannelCommunicator
import lab7.server.console.ConsoleHandler
import lab7.server.handler.CommandsRequestHandler
import lab7.shared.Config
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import kotlin.concurrent.thread

fun main() {
    val port = Config.SERVER_PORT
    val hikariConfig = HikariConfig("hikari.properties")
    val hikariDataSource = HikariDataSource(hikariConfig)
    val databaseHandler = PostgresHandler(hikariDataSource)
    val organizationCollection: CollectionManager<Organization> = OrganizationCollection(databaseHandler)
    val userManager = UserDatabaseManager(hikariDataSource)
    val handler = CommandsRequestHandler(organizationCollection, userManager)
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

