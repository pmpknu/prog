package lab7.server.communicator

import lab7.shared.communicator.Communicator
import org.slf4j.LoggerFactory
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

/**
 * Класс коммуникатора использующий DatagramChannel
 *
 * @property channel канал для коммуникаций
 */
class DatagramChannelCommunicator(
    private val channel: DatagramChannel,
    private val chunkSize: Int,
): Communicator {
    init {
        assert(!channel.isBlocking) {
            "channel must be non-blocking"
        }
    }

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun receiveData(): Pair<ByteArray, SocketAddress> {
        logger.info("Получаем данные от клиента")
        val dst = ByteBuffer.allocate(chunkSize)
        var addr: SocketAddress? = null
        while(addr == null) {
            addr = channel.receive(dst)
            Thread.sleep(5)
        }
        val dstByteArray = dst.toByteArray()
        logger.info("Получили данные от клиента")
        return Pair(dstByteArray, addr)
    }

    override fun sendData(data: ByteArray, addr: SocketAddress) {
        logger.info("Отправка данных из ${data.size} байт.")
        val totalChunks = (data.size).ceilDiv(chunkSize)
        val chunkedData = data.toList()
            .chunked(chunkSize)
        logger.info("Всего чанков: $totalChunks")

        chunkedData.forEachIndexed { ind, it ->
            val toSend = it.toMutableList()
            if(ind < totalChunks - 1) {
                toSend.add(1.toByte())
            } else {
                toSend.add(0.toByte())
            }
            val buf = ByteBuffer.wrap(toSend.toByteArray())
            channel.send(buf, addr)
            logger.info("Отправлен чанк #${ind+1}")
//            logger.info("Последний бит чанка: ${toSend.last()}")
            logger.info("Размер чанка: ${toSend.size}")
//            logger.info("=======")
            Thread.sleep(10)
        }
    }
}


/**
 * Целочисленное с округлением вверх
 *
 * @param b
 */
fun Int.ceilDiv(b: Int) = (this + b - 1).div(b)

fun ByteBuffer.toByteArray(): ByteArray {
    return this.array()
}