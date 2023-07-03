package lab7.shared.entities.user

import kotlinx.serialization.Serializable
import lab7.shared.entities.dto.commands.UUIDSerializer
import java.util.*

@Serializable data class User(val id: Int, val name: String, val password: String)