package lab5.entities.organization

import lab5.entities.CorrectnessException
import lab5.entities.Entity
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
data class Coordinates(
    @XmlElement
    val x: Float = 0f,
    @XmlElement
    val y: Double = 0.0
) : Entity<Coordinates> {
    override fun checkCorrectness() {
        if (y > 883)
            throw CorrectnessException("y", "should be less than 883")
    }

    override fun compareTo(other: Coordinates): Int {
        return (this.x * this.x + this.y * this.y - (other.x * other.x + other.y * other.y)).toInt()
    }

    override fun toString(): String {
        return "x=$x, y=$y"
    }
}