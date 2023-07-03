package lab7.shared.entities.organization

class OrganizationComparator: Comparator<Organization> {
    override fun compare(o1: Organization?, o2: Organization?): Int {
        if (o1 == null)
            if (o2 == null)
                return  0
            else
                return -1

        if (o2 == null)
            return 1

        return o1.annualTurnover.compareTo(o2.annualTurnover)
    }

}