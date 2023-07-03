package lab7.shared.entities.organization

class AddressComparator: Comparator<Address> {
    override fun compare(o1: Address?, o2: Address?): Int {
        if (o1 == null)
            if (o2 == null)
                return  0
            else
                return -1

        if (o2 == null)
            return 1

        return (o1.town.x * o1.town.x + o1.town.y * o1.town.x)
            .compareTo(o2.town.x * o2.town.x + o2.town.y * o2.town.x)
    }
}