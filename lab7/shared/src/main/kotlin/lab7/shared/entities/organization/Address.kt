package lab7.shared.entities.organization

import kotlinx.serialization.Serializable
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
@Serializable
data class Address(
    @XmlElement
    val street: String = "",
    @XmlElement
    val town: Location = Location()
) : Comparable<Address?> {
    override fun toString(): String {
        return "Улица: $street, Город: $town"
    }

    override fun compareTo(other: Address?): Int {
        return AddressComparator().compare(this, other)
    }
}