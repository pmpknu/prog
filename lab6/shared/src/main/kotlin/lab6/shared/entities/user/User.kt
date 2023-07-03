package lab6.shared.entities.user

import kotlinx.serialization.Serializable
import lab6.shared.entities.dto.commands.UUIDSerializer
import java.util.*

@Serializable data class User(@Serializable(with = UUIDSerializer::class) val id: UUID)