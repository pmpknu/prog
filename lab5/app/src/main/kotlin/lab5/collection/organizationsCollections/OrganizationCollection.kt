package lab5.collection.organizationsCollections

import lab5.collection.CollectionInfo
import lab5.collection.CollectionManager
import lab5.collection.filehandler.CollectionFileHandler
import lab5.collection.filehandler.XmlHandler
import lab5.entities.organization.*
import java.io.File
import java.time.LocalDate
import java.util.HashSet


/**
 * Реализация менеджера коллекции для типа Организация.
 * @property creationDate Дата создания коллекции.
 * @property coll Коллекция.
 */
class OrganizationCollection(private val fileHandler: CollectionFileHandler): CollectionManager<Organization> {
    private val creationDate = java.time.LocalDateTime.now()
    private var coll = HashSet<Organization>()
    private var currID: Int = 1

    constructor() : this(fileHandler = XmlHandler(File("testfile")))

    init {
        if(loadFromFile())
            println("Загружена коллекция по умолчанию")
        else
            println("Не удалось загрузить коллекцию по умолчанию")
    }

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
    private fun generateID() : Int {
        return currID++
    }

    /**
     * Обновляет ID.
     */
    private fun updateID() {
        var maxId = 0
        coll.forEach { if (it.id > maxId) maxId = it.id }
        currID = maxId+1
    }

    override fun add(item: Organization) : Boolean {
        item.copy(id = generateID(), creationDate = LocalDate.now()).also {
            it.checkCorrectness()
            return coll.add(it)
        }
    }

    override fun addIfMin(item: Organization) : Boolean {
        item.checkCorrectness()
        if (item < coll.min())
            return this.add(item)
        return false
    }

    override fun addIfMax(item: Organization) : Boolean {
        item.checkCorrectness()
        if (item > coll.max())
            return this.add(item)
        return false
    }

    override fun updateElementByID(item: Organization) : Boolean {
        item.checkCorrectness()
        this.removeElementByID(item.id)
        return this.add(item)
    }

    override fun removeElementByID(id: Int) : Boolean {
        return coll.remove(
            coll.find {it.id == id}
        )
    }

    override fun clear() {
        coll.removeAll(coll)
    }

    override fun show(): Iterable<Organization> {
        return coll.toList()
    }

    override fun saveToFile() : Boolean {
        if (fileHandler.canWrite())
            return fileHandler.saveCollection(coll)
        else {
            println("Нет прав на запись")
            return false
        }
    }

    override fun loadFromFile(): Boolean {
        (fileHandler.loadCollection() as HashSet<Organization>?)?.also { this.coll = it }
        updateID()
        return true
    }

    override fun returnMaxIdElement(): Organization? {
        var i = currID
        while (i > 0 && coll.find { it.id == i } == null) {
            i--
        }
        currID = i
        return coll.find { it.id == currID }
    }

    override fun returnDescending(): Iterable<Organization> {
        return coll.sortedByDescending { it.annualTurnover }
    }

    override fun removeLowerThen(item: Organization) : Boolean {
        var flag = true
        coll
            .filter { it < item }
            .forEach { flag = flag && coll.remove(it) }
        return flag
    }

    /**
     * Возвращает список всех элеметов, адресс которых больше заданного.
     * @param address Адрес для сравния
     * @return Список организаций больших по адресу
     */
    fun filterGreaterThanPostalAddress(address: Address): Iterable<Organization> {
        return coll.filter { address < it.postalAddress }
    }
}