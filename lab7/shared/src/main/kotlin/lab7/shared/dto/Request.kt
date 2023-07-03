package lab7.shared.dto
import kotlinx.serialization.Serializable
import lab7.shared.entities.organization.Address
import lab7.shared.entities.organization.Organization
import lab7.shared.entities.user.User

/**
 * Запечатанный класс Request нужен для обозначения возможных запросов
 * @constructor Create empty Request
 */
@Serializable
sealed class Request {

    @Serializable
    abstract val user: User

    @Serializable
    data class AddRequest(val organization: Organization, override val user: User): Request()
    @Serializable
    data class AddIfMaxRequest(val organization: Organization, override val user: User): Request()
    @Serializable
    data class AddIfMinRequest(val organization: Organization, override val user: User): Request()
    @Serializable
    class ClearRequest(override val user: User) : Request()
    @Serializable
    data class FilterGreaterRequest(val address: Address, override val user: User) : Request()
    @Serializable
    class InfoRequest(override val user: User) : Request()
    @Serializable
    class MaxByIdRequest(override val user: User) : Request()
    @Serializable
    class PrintDescendingRequest(override val user: User) : Request()
    @Serializable
    data class RemoveRequest(val id: Int, override val user: User) : Request()
    @Serializable
    class RemoveLowerRequest(val organization: Organization, override val user: User) : Request()
    @Serializable
    class ShowRequest(override val user: User) : Request()
    @Serializable
    data class UpdateRequest(val organization: Organization, override val user: User) : Request()

    @Serializable
    data class AuthorizeRequest(override val user: User): Request()
}
