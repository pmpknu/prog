package lab5.entities.organization

import lab5.entities.CorrectnessException
import lab5.entities.Entity
import java.io.BufferedReader
import java.io.Reader
import java.time.LocalDate

import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlAdapter
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

@XmlRootElement(name = "Organization")
@XmlAccessorType(XmlAccessType.FIELD)
data class Organization(
    @XmlAttribute
    var id: Int = 0,
    @XmlElement
    val name: String = "",
    @XmlElement
    val coordinates: Coordinates = Coordinates(),
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter::class)
    @XmlSchemaType(name = "date")
    val creationDate: LocalDate = LocalDate.now(),
    @XmlElement
    val annualTurnover: Long = 0,
    @XmlElement
    val type: OrganizationType? = null,
    @XmlElement
    val postalAddress: Address? = null
): Entity<Organization> {
    constructor(
        name: String,
        coordinates: Coordinates,
        annualTurnover: Long,
        type: OrganizationType?,
        postalAddress: Address?
    ) : this(
        id = 0,
        name = name,
        coordinates = coordinates,
        creationDate = LocalDate.now(),
        annualTurnover = annualTurnover,
        type = type,
        postalAddress = postalAddress
    )

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