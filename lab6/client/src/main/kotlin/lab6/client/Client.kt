package lab6.client

import lab6.client.communicator.DatagramSocketCommunicator
import lab6.client.console.ConsoleHandler
import lab6.client.handler.CommunicatorHandler
import lab6.shared.Config
import lab6.shared.communicator.Communicator
import lab6.shared.entities.user.User
import java.io.BufferedReader
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.*

fun main() {
    val serverPort = Config.SERVER_PORT
    val chunkSize = Config.CHUNK_SIZE
    val socket = DatagramSocket()
    val communicator = DatagramSocketCommunicator(socket, chunkSize)
    val handler = CommunicatorHandler(InetSocketAddress(serverPort), User(UUID.randomUUID()), communicator)
    val consoleHandler = ConsoleHandler(handler, reader = System.`in`.reader().buffered())
    consoleHandler.loop()
}
