package lab6.shared.entities.organization

import java.time.LocalDate
import javax.xml.bind.annotation.*

@XmlRootElement(name = "Organization")
@XmlAccessorType(XmlAccessType.FIELD)
data class OrganizationWrapper(
    @XmlAttribute
    var id: Int = 0,
    @XmlElement
    val name: String = "",
    @XmlElement
    val coordinates: Coordinates = Coordinates(),
    @XmlElement
    val creationDate: String = LocalDate.now().toString(),
    @XmlElement
    val annualTurnover: Long = 0,
    @XmlElement
    val type: OrganizationType? = null,
    @XmlElement
    val postalAddress: Address? = null
) {
    constructor(organization: Organization) : this(
    id = organization.id,
    name = organization.name,
    coordinates = organization.coordinates,
    creationDate = organization.creationDate.toString(),
    annualTurnover = organization.annualTurnover,
    type = organization.type,
    postalAddress = organization.postalAddress
    )

    fun returnOranization() : Organization {
        return Organization(
            id = this.id,
            name = this.name,
            coordinates = this.coordinates,
            creationDate = LocalDate.parse(this.creationDate),
            annualTurnover = this.annualTurnover,
            type = this.type,
            postalAddress = this.postalAddress
        )
    }
}