package lab5.entities.organization

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
data class Location(
    @XmlElement
    val y: Long = 0,
    @XmlElement
    val x: Int = 0,
    @XmlElement
    val name: String = ""
) : Comparable<Location?> {
    override fun toString(): String {
        return "$name (x=$x, y=$y)"
    }

    override fun compareTo(other: Location?): Int {
        return LocationComparator().compare(this, other)
    }
}