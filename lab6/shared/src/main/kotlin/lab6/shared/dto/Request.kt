package lab6.shared.dto
import kotlinx.serialization.Serializable
import lab6.shared.entities.organization.Address
import lab6.shared.entities.organization.Organization

/**
 * Запечатанный класс Request нужен для обозначения возможных запросов
 * @constructor Create empty Request
 */
@Serializable
sealed class Request {
    @Serializable
    data class AddRequest(val organization: Organization): Request()
    @Serializable
    data class AddIfMaxRequest(val organization: Organization): Request()
    @Serializable
    data class AddIfMinRequest(val organization: Organization): Request()
    @Serializable
    class ClearRequest() : Request()
    @Serializable
    data class FilterGreaterRequest(val address: Address) : Request()
    @Serializable
    class InfoRequest() : Request()
    @Serializable
    class MaxByIdRequest() : Request()
    @Serializable
    class PrintDescendingRequest() : Request()
    @Serializable
    data class RemoveRequest(val id: Int) : Request()
    @Serializable
    class RemoveLowerRequest(val organization: Organization) : Request()
    @Serializable
    class ShowRequest() : Request()
    @Serializable
    data class UpdateRequest(val organization: Organization) : Request()
}
