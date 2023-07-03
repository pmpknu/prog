package lab7.client

import lab7.client.communicator.DatagramSocketCommunicator
import lab7.client.console.ConsoleHandler
import lab7.client.handler.CommunicatorHandler
import lab7.shared.Config
import lab7.shared.communicator.Communicator
import lab7.shared.entities.user.User
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
    val user = requestUser()
    val handler = CommunicatorHandler(InetSocketAddress(serverPort), user, communicator)
    val consoleHandler = ConsoleHandler(handler, reader = System.`in`.reader().buffered())
    consoleHandler.loop()
}

fun requestUser(): User {
    //return User(-1, "tset", "tset")
    val console = System.console()
    println("Введите имя пользователя")
    val name = console.readLine()
    println("Введите пароль")
    val password = console.readPassword().toString()
    return User(-1, name, password)
}
