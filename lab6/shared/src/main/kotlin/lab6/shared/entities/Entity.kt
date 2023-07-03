package lab6.shared.entities

interface Entity<T>: Comparable<T> {
    fun checkCorrectness()
    override fun compareTo(other: T): Int
}