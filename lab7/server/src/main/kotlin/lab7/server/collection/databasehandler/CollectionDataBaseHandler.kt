package lab7.server.collection.databasehandler

import lab7.shared.entities.organization.Organization
import java.util.HashSet

interface CollectionDataBaseHandler {
    fun loadCollection() : Set<Organization>
    fun removeAll(authorId: Int) : Boolean
    fun add(item: Organization) : Int
    fun delete(id: Int, authorId: Int)   : Boolean
    fun update(item: Organization, authorId: Int) : Boolean
}