package lab6.shared.dto

import kotlinx.serialization.Serializable
import lab6.shared.entities.organization.Organization
import lab6.shared.entityCollection.CollectionInfo

/**
 * Запечатанный класс Response нужен для обозначения возможных запросов
 * @constructor Create empty Response
 */
@Serializable
sealed class Response {
    @Serializable
    data class AddResponse(val success: Boolean) : Response()
    @Serializable
    data class AddIfMaxResponse(val success: Boolean): Response()
    @Serializable
    data class AddIfMinResponse(val success: Boolean): Response()
    @Serializable
    class ClearResponse(val unit: Unit) : Response()
    @Serializable
    data class FilterGreaterResponse(val coll: List<Organization>) : Response()
    @Serializable
    data class InfoResponse(val info: CollectionInfo) : Response()
    @Serializable
    data class MaxByIdResponse(val organization: Organization?) : Response()
    @Serializable
    data class PrintDescendingResponse(val coll: List<Organization>) : Response()
    @Serializable
    data class RemoveResponse(val success: Boolean) : Response()
    @Serializable
    data class RemoveLowerResponse(val success: Boolean) : Response()
    @Serializable
    data class ShowResponse(val coll: List<Organization>) : Response()
    @Serializable
    data class UpdateResponse(val success: Boolean) : Response()
    @Serializable
    class CommandNotFoundResponse(): Response()
}
