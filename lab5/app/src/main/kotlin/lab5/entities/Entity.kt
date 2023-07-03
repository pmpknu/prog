package lab5.entities

import lab5.entities.organization.Organization
import kotlin.jvm.Throws

interface Entity<T>: Comparable<T> {
    fun checkCorrectness()
    override fun compareTo(other: T): Int
}