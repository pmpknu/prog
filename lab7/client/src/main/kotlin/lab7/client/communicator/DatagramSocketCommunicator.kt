package lab7.client.communicator

import lab7.shared.communicator.Communicator
import org.junit.rules.Timeout
import java.io.FileNotFoundException
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketAddress
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

class DatagramSocketCommunicator(
    private val socket: DatagramSocket,
    private val chunkSize: Int
): Communicator {
    init {
        socket.soTimeout = 1000;
    }

    override fun receiveData(): Pair<ByteArray, SocketAddress> {
        println("Получаем данные от сервера")
        val totalData = MutableList<Byte>(0, {42.toByte()})
        val buf = ByteArray(chunkSize + 1)
        val packet = DatagramPacket(buf, buf.size)
        var chunkId = 0
        var sm = 0;
        while(true) {
            println("Получаем чанк #${chunkId+1}")
            socket.receive(packet)
            println("Получили чанк #${chunkId+1}")
            val curData = packet.data
            sm += packet.length
            val last = curData[packet.length - 1]
            println("Последний бит чанка: $last")
            println("Размер чанка: ${packet.length}")
            println("========")
            totalData += curData.slice(packet.offset until packet.length - 1)
            if(last == 0.toByte()) {
                break;
            }
            chunkId++;
        }
        println("Получили все чанки от сервера")
        println("Суммарный размер: ${totalData.size}. Сумма размеров чанков: $sm")
        return Pair(totalData.toByteArray(), packet.socketAddress)
    }

    override fun sendData(data: ByteArray, addr: SocketAddress) {
        val dp = DatagramPacket(data, data.size, addr)
        socket.send(dp)
    }
}