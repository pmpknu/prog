package lab7.server.collection.organizationsCollections

import lab7.server.collection.databasehandler.CollectionDataBaseHandler
import lab7.shared.entityCollection.CollectionInfo
import lab7.shared.entities.organization.*
import org.slf4j.LoggerFactory
import java.time.LocalDate


/**
 * Реализация менеджера коллекции для типа Организация.
 * @property dbHandler База данных.
 * @property creationDate Дата создания коллекции.
 * @property coll Коллекция.
 */
class OrganizationCollection(private val dbHandler: CollectionDataBaseHandler): CollectionManager<Organization> {
    private val creationDate = java.time.LocalDateTime.now()
    @get:Synchronized
    @set:Synchronized
    private var coll = java.util.HashSet<Organization>()
    private var currID: Int = 1

    init {
        if(load())
            println("Загружена коллекция по умолчанию")
        else
            println("Не удалось загрузить коллекцию по умолчанию")
    }

    @Synchronized
    override fun info(): CollectionInfo {
        return CollectionInfo(
            type = this.javaClass.simpleName,
            initDate = creationDate,
            collSize = coll.size
        )
    }

    /**
     * Генерирует ID.
     * @return Сгенерированный ID
     */
    @Synchronized
    private fun generateID() : Int {
        return currID++
    }

    @Synchronized
    override fun add(item: Organization) : Boolean {
        val id = dbHandler.add(item)
        item.copy(id = id, creationDate = LocalDate.now()).also {
            return coll.add(it)
        }
    }

    @Synchronized
    override fun addIfMin(item: Organization) : Boolean {
        if (item < coll.min())
            return this.add(item)
        return false
    }

    @Synchronized
    override fun addIfMax(item: Organization) : Boolean {
        if (item > coll.max())
            return this.add(item)
        return false
    }

    @Synchronized
    override fun updateElementByID(item: Organization, authorId: Int) : Boolean {
        item.checkCorrectness()
        val isUpdated = dbHandler.update(item, authorId)
        if (isUpdated) {
            coll.remove(
                coll.find {it.id == item.id}
            )
            return coll.add(item)
        }
        return isUpdated
    }

    @Synchronized
    override fun removeElementByID(id: Int, authorId: Int) : Boolean {
        val isDel = dbHandler.delete(id, authorId)
        if(isDel) {
            return coll.remove(
                coll.find {it.id == id}
            )
        }
        return isDel
    }

    @Synchronized
    override fun clear(authorId: Int) {
        dbHandler.removeAll(authorId)
        coll.removeAll(coll)
    }

    @Synchronized
    override fun show(): Iterable<Organization> {
        return coll.toList()
    }

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Synchronized
    override fun load(): Boolean {
        dbHandler.loadCollection().forEach {
            coll.add(it)
        }
        return true
    }

    @Synchronized
    override fun returnMaxIdElement(): Organization? {
        var i = currID
        while (i > 0 && coll.find { it.id == i } == null) {
            i--
        }
        currID = i
        return coll.find { it.id == currID }
    }

    @Synchronized
    override fun returnDescending(): Iterable<Organization> {
        return coll.sortedByDescending { it.annualTurnover }
    }

    @Synchronized
    override fun removeLowerThen(item: Organization, authorId: Int) : Boolean {
        var flag = true
        coll
            .filter { it < item }
            .forEach { flag = flag && coll.remove(it) && dbHandler.delete(it.id, authorId) }
        return flag
    }

    @Synchronized
    override fun filterGreaterThanPostalAddress(address: Address): Iterable<Organization> {
        return coll.filter { address < it.postalAddress }
    }
}