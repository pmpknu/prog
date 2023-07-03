package lab5.collection.filehandler

import lab5.entities.organization.Organization
import java.util.HashSet

interface CollectionFileHandler {
    fun loadCollection() : HashSet<Organization>?
    fun saveCollection(coll: HashSet<Organization>) : Boolean
    fun canRead() : Boolean
    fun canWrite() : Boolean
}