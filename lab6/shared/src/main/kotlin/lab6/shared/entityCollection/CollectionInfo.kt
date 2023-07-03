package lab6.shared.entityCollection

import kotlinx.serialization.Serializable
import lab6.shared.entities.organization.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class CollectionInfo(
    val type: String,
    @Serializable(with = LocalDateTimeSerializer::class) val initDate: LocalDateTime,
    val collSize: Int
) {
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
        return "Тип: $type\nДата инициализации: ${initDate.format(formatter)}\nКоличество элементов: $collSize"
    }
}