package lab5.entities.organization

import java.time.LocalDate
import javax.xml.bind.annotation.adapters.XmlAdapter

class LocalDateAdapter : XmlAdapter<String, LocalDate>() {
    override fun marshal(value: LocalDate?): String? {
        return value?.toString()
    }

    override fun unmarshal(value: String?): LocalDate? {
        return if (value == null || value.isEmpty()) {
            null
        } else {
            LocalDate.parse(value)
        }
    }
}

/*Ы
@XmlJavaTypeAdapter(value = LocalDateAdapter::class)
@XmlAccessorType(XmlAccessType.FIELD)
data class LocalDateAdapter(
    @XmlValue
    val date: LocalDate
) : XmlAdapter<String, LocalDate>() {
    override fun marshal(value: LocalDate?): String? {
        return value?.toString()
    }
    override fun unmarshal(value: String?): LocalDate? = LocalDate.parse(value)
}
*/
