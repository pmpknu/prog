package lab7.server.threads

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lab7.shared.dto.Request
import lab7.shared.dto.Response
import java.nio.charset.Charset
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class RequestWriter {
    private val pool = Executors.newCachedThreadPool()

    fun writeRequest(response: Response): ByteArray {
        val jsonStr = Json
            .encodeToString<Response>(response)
            .replace((0).toChar().toString(), "")

        return jsonStr.toByteArray(Charset.forName("UTF-8"))
    }
}