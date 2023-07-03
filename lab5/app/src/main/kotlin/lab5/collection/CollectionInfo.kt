package lab5.collection

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




data class CollectionInfo(
    val type: String,
    val initDate: LocalDateTime,
    val collSize: Int
) {
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
        return "Тип: $type\nДата инициализации: ${initDate.format(formatter)}\nКоличество элементов: $collSize"
    }
}