package lab7.server.threads

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lab7.shared.dto.Request
import java.nio.charset.Charset
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class RequestReader {
    private val pool = Executors.newCachedThreadPool()

    fun readRequest(data: ByteArray): Request {
        val task: Callable<Request> = Callable<Request> {
            val dataStr = data
                .toString(Charset.forName("UTF-8"))
                .replace((0).toChar().toString(), "")
            Json.decodeFromString<Request>(dataStr)
        }

        return pool.submit(task).get()
    }
}