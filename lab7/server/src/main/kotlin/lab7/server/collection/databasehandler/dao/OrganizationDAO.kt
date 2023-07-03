package lab7.server.collection.databasehandler.dao

import lab7.shared.entities.organization.Address
import lab7.shared.entities.organization.OrganizationType

data class OrganizationDAO(
    val id: Int,
    val name: String,
    val creationDate: java.time.LocalDateTime,
    val type: OrganizationType?,
    val annualTurnover: Long,
    val postalAddressId: Int?,
    val authorId: Int,
)