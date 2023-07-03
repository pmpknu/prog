package lab7.server

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import lab7.server.handler.RequestHandler
import lab7.shared.communicator.Communicator
import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Organization
import lab7.server.collection.organizationsCollections.CollectionManager
import org.slf4j.LoggerFactory
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.nio.charset.Charset


/**
 * Server
 *
 * @property communicator
 * @property manager
 * @constructor Create empty Server
 */
class Server(
    private val communicator: Communicator,
    private val handler: RequestHandler,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun run() {
        logger.info("Запускаем сервер.")
        while (true) {
            logger.info("Ждем данные от клиента...")
            val data = communicator.receiveData()
            logger.info("Получены данные от клиента ${data.second}")
            val dataStr = data.first
                .toString(Charset.forName("UTF-8"))
                .replace((0).toChar().toString(), "")
            logger.info("Данные от клиента: $dataStr")
            val request = Json.decodeFromString<Request>(dataStr)
            logger.info("Запрос: ${request::class.simpleName}")
            handler.handleRequest(request) { response ->
                val jsonStr = Json
                    .encodeToString<Response>(response)
                    .replace((0).toChar().toString(), "")
                logger.info("Длина овтета для клиента: ${jsonStr.length}")
                communicator.sendData(
                    jsonStr.toByteArray(Charset.forName("UTF-8")),
                    data.second,
                )
                logger.info("Отправлен ответ клиенту ${data.second}")
            }
        }
    }
}