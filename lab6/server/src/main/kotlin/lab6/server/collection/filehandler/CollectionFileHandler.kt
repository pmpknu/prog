package lab6.server.collection.filehandler

import lab6.shared.entities.organization.Organization
import java.util.HashSet

interface CollectionFileHandler {
    fun loadCollection() : HashSet<Organization>?
    fun saveCollection(coll: HashSet<Organization>) : Boolean
    fun canRead() : Boolean
    fun canWrite() : Boolean
}