package lab6.client.handler

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import lab6.shared.communicator.Communicator
import lab6.shared.dto.Request
import lab6.shared.dto.Response
import lab6.shared.entities.organization.Address
import lab6.shared.entities.organization.Organization
import lab6.shared.entities.user.User
import lab6.shared.entityCollection.CollectionInfo
import lab6.shared.entityCollection.CollectionManager
import java.net.SocketAddress
import java.nio.charset.Charset

/**
 * UDP handler.
 * Класс получения и отправки команд [CollectionManager] с помощью протокола UDP.
 * @property user пользователь
 */
class CommunicatorHandler(
    private val serverAddress: SocketAddress,
    private val user: User,
    private val communicator: Communicator,
) : CollectionManager<Organization> {

    private val serializer = Json {
        ignoreUnknownKeys = true
    }

    fun sendRequest(request: Request): Response {
        val requestJson = serializer
            .encodeToString(request)
            .replace((0).toChar().toString(), "")
        println("Отправляем запрос на сервер. Запрос в виде json: $requestJson")
        val requestByteArray = requestJson
            .toByteArray(Charset.forName("UTF-8"))
        communicator.sendData(requestByteArray, serverAddress)
        val data = communicator.receiveData()
        val dataJson = data.first
            .toString(Charset.forName("UTF-8"))
            .replace((0).toChar().toString(), "")
//        println("Ответ от сервера: $dataJson")
        println("Длина ответа от сервера (как строка): ${dataJson.length}")
        return serializer.decodeFromString(dataJson)
    }

    override fun info(): CollectionInfo {
        return (sendRequest(Request.InfoRequest()) as Response.InfoResponse).info
    }

    override fun clear() {
        (sendRequest(Request.ClearRequest()) as Response.ClearResponse).unit
    }

    override fun show(): Iterable<Organization> {
        return (sendRequest(Request.ShowRequest()) as Response.ShowResponse).coll
    }

    override fun removeElementByID(id: Int): Boolean {
        return (sendRequest(Request.RemoveRequest(id)) as Response.RemoveResponse).success
    }

    override fun saveToFile(): Boolean {
        return false
    }

    override fun loadFromFile(): Boolean {
        return false
    }

    override fun returnMaxIdElement(): Organization? {
        return (sendRequest(Request.MaxByIdRequest()) as Response.MaxByIdResponse).organization
    }

    override fun returnDescending(): Iterable<Organization> {
        return (sendRequest(Request.PrintDescendingRequest()) as Response.PrintDescendingResponse).coll
    }

    override fun filterGreaterThanPostalAddress(address: Address): Iterable<Organization> {
        return (sendRequest(Request.FilterGreaterRequest(address)) as Response.FilterGreaterResponse).coll
    }

    override fun removeLowerThen(item: Organization): Boolean {
        return (sendRequest(Request.RemoveLowerRequest(item)) as Response.RemoveLowerResponse).success
    }

    override fun addIfMin(item: Organization): Boolean {
        return (sendRequest(Request.AddIfMinRequest(item)) as Response.AddIfMinResponse).success
    }

    override fun addIfMax(item: Organization): Boolean {
        return (sendRequest(Request.AddIfMaxRequest(item)) as Response.AddIfMaxResponse).success
    }

    override fun updateElementByID(item: Organization): Boolean {
        return (sendRequest(Request.UpdateRequest(item)) as Response.UpdateResponse).success
    }

    override fun add(item: Organization): Boolean {
        return (sendRequest(Request.AddRequest(item)) as Response.AddResponse).success
    }

}