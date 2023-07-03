package lab6.shared.entities.organization

import javax.xml.bind.annotation.*

@XmlRootElement(name = "Organizations")
@XmlAccessorType(XmlAccessType.FIELD)
data class OrganizationsSet(
    var organization: HashSet<OrganizationWrapper> = HashSet()
) {
    constructor() : this(HashSet())
}

/*
@XmlRootElement
class HashSetWrapper<T>(
    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    var set: HashSet<T> = HashSet()
)
*/
