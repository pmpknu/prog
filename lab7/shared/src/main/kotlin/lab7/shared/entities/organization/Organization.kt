package lab7.shared.entities.organization

import kotlinx.serialization.Serializable
import lab7.shared.entities.CorrectnessException
import lab7.shared.entities.Entity
import java.time.LocalDate

import javax.xml.bind.annotation.*

@XmlRootElement(name = "Organization")
@XmlAccessorType(XmlAccessType.FIELD)
@Serializable
data class Organization(
    var id: Int = 0,
    val name: String = "",
    val coordinates: Coordinates = Coordinates(),
    @Serializable(with = LocalDateSerializer::class)
    val creationDate: LocalDate = LocalDate.now(),
    val annualTurnover: Long = 0,
    val type: OrganizationType? = null,
    val postalAddress: Address? = null,
    val authorId: Int,
): Entity<Organization> {

    override fun checkCorrectness() {
        if (id <= 0)
            throw CorrectnessException("id", "should be greater than 0")
        if (name.isBlank())
            throw CorrectnessException("name", "shouldn't contain only whitespace characters")
        coordinates.checkCorrectness()
        if (annualTurnover <= 0)
            throw CorrectnessException("annualTurnover", "should be greater than 0")
    }

    override fun compareTo(other: Organization): Int {
        return OrganizationComparator().compare(this, other)
    }

    override fun toString(): String {
        return "Организация #$id\nНазвание: $name\nКоординаты: $coordinates\nДата создания: $creationDate\nОборот: $annualTurnover\nТип: $type\nПочтовый адресс: $postalAddress"
    }
}