package lab7.shared.communicator

import java.net.SocketAddress

/**
 * Класс, ответственный за получение и передачу данных
 * в виде массива байтов
 *
 * @constructor Create empty Communicator
 */
interface Communicator {
    fun receiveData(): Pair<ByteArray, SocketAddress>
    fun sendData(data: ByteArray, addr: SocketAddress)
}