package lab7.shared.entities.organization

class LocationComparator: Comparator<Location> {
    override fun compare(o1: Location?, o2: Location?): Int {
        if (o1 == null)
            if (o2 == null)
                return  0
            else
                return -1

        if (o2 == null)
            return 1

        return (o1.x * o1.x + o1.y * o1.y).compareTo(o2.x * o2.x + o2.y * o2.y)
    }

}
