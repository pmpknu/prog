package lab7.client.handler

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import lab7.client.collection.CollectionManager
import lab7.shared.communicator.Communicator
import lab7.shared.dto.Request
import lab7.shared.dto.Response
import lab7.shared.entities.organization.Address
import lab7.shared.entities.organization.Organization
import lab7.shared.entities.user.User
import lab7.shared.entityCollection.CollectionInfo
import java.net.SocketAddress
import java.nio.charset.Charset
import kotlin.system.exitProcess

/**
 * UDP handler.
 * Класс получения и отправки команд [CollectionManager] с помощью протокола UDP.
 * @property user пользователь
 */
class CommunicatorHandler(
    private val serverAddress: SocketAddress,
    private var user: User,
    private val communicator: Communicator,
) : CollectionManager<Organization> {

    init {
        val request = Request.AuthorizeRequest(user)
        println(request.toString())
        val response = (sendRequest(request)) as Response.AuthorizeResponse
        println(response)
        if(response.authorizedUser == null) {
            println("Неправильное имя или пароль!")
            exitProcess(-1)
        }
        else {
            user = response.authorizedUser!!
        }
    }

    private val serializer = Json {
//        ignoreUnknownKeys = true??
    }

    private fun sendRequest(request: Request): Response {
        val requestJson = Json
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
        println("Ответ от сервера: $dataJson")
        println("Длина ответа от сервера (как строка): ${dataJson.length}")
        return Json.decodeFromString(dataJson)
    }

    override fun info(): CollectionInfo {
        return (sendRequest(Request.InfoRequest(user)) as Response.InfoResponse).info
    }

    override fun clear() {
        (sendRequest(Request.ClearRequest(user)) as Response.ClearResponse).unit
    }

    override fun show(): Iterable<Organization> {
        return (sendRequest(Request.ShowRequest(user)) as Response.ShowResponse).coll
    }

    override fun removeElementByID(id: Int): Boolean {
        return (sendRequest(Request.RemoveRequest(id, user)) as Response.RemoveResponse).success
    }

    override fun load(): Boolean {
        return false
    }

    override fun returnMaxIdElement(): Organization? {
        return (sendRequest(Request.MaxByIdRequest(user)) as Response.MaxByIdResponse).organization
    }

    override fun returnDescending(): Iterable<Organization> {
        return (sendRequest(Request.PrintDescendingRequest(user)) as Response.PrintDescendingResponse).coll
    }

    override fun filterGreaterThanPostalAddress(address: Address): Iterable<Organization> {
        return (sendRequest(Request.FilterGreaterRequest(address, user)) as Response.FilterGreaterResponse).coll
    }

    override fun removeLowerThen(item: Organization): Boolean {
        return (sendRequest(Request.RemoveLowerRequest(item, user)) as Response.RemoveLowerResponse).success
    }

    override fun addIfMin(item: Organization): Boolean {
        return (sendRequest(Request.AddIfMinRequest(item, user)) as Response.AddIfMinResponse).success
    }

    override fun addIfMax(item: Organization): Boolean {
        return (sendRequest(Request.AddIfMaxRequest(item, user)) as Response.AddIfMaxResponse).success
    }

    override fun updateElementByID(item: Organization): Boolean {
        return (sendRequest(Request.UpdateRequest(item, user)) as Response.UpdateResponse).success
    }

    override fun add(item: Organization): Boolean {
        return (sendRequest(Request.AddRequest(item, user)) as Response.AddResponse).success
    }

}