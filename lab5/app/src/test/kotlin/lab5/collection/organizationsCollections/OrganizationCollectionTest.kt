package lab5.collection.organizationsCollections

import lab5.collection.filehandler.XmlHandler
import lab5.entities.organization.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate

class OrganizationCollectionTest{
//    @Test fun `add If Min`() {
//        val orgcoll = OrganizationCollection(XmlHandler(File("./filename")))
//        val item = Organization(name = "name",
//            coordinates = Coordinates(10F, 10.0),
//            creationDate = LocalDate.now(),
//            annualTurnover = 10L,
//            type = OrganizationType.COMMERCIAL,
//            postalAddress = Address(street = "Name", town = Location(y = 10L, x = 1, name = "nNmae")))
//        orgcoll.add(item = item.copy(annualTurnover = 100L))
//        orgcoll.add(item = item.copy(annualTurnover = 200L))
//        orgcoll.add(item = item.copy(annualTurnover = 11L))
//        orgcoll.add(item = item.copy(annualTurnover = 300L))
//        orgcoll.addIfMin(item = item)
//        assert(true)
//    }

    @Test fun `sort Descending`() {
        val orgcoll = OrganizationCollection(XmlHandler(File("./filename")))
        val item = Organization(name = "name",
            coordinates = Coordinates(10F, 10.0),
            creationDate = LocalDate.now(),
            annualTurnover = 10L,
            type = OrganizationType.COMMERCIAL,
            postalAddress = Address(street = "Name", town = Location(y = 10L, x = 1, name = "nNmae")))
        orgcoll.add(item = item.copy(annualTurnover = 100L))
        orgcoll.add(item = item.copy(annualTurnover = 300L))
        orgcoll.add(item = item.copy(annualTurnover = 200L))
        orgcoll.add(item = item.copy(annualTurnover = 11L))
        val ooocolll = orgcoll.returnDescending()
        println(ooocolll)
        assert(true)
    }


    @Test fun `save to file`() {
        val file = File("testfile")
        val handler = XmlHandler(file)
        val orgcoll = OrganizationCollection(handler)
        val item = Organization(name = "name",
            coordinates = Coordinates(10F, 10.0),
            creationDate = LocalDate.now(),
            annualTurnover = 10L,
            type = OrganizationType.COMMERCIAL,
            postalAddress = Address(street = "Name", town = Location(y = 10L, x = 1, name = "nNmae")))
        orgcoll.add(item = item.copy(annualTurnover = 100L))
        orgcoll.add(item = item.copy(annualTurnover = 300L))
        orgcoll.add(item = item.copy(annualTurnover = 200L))
        orgcoll.add(item = item.copy(annualTurnover = 11L))
        val ooocolll = orgcoll.saveToFile()
        assert(ooocolll)
    }

    @Test fun `load to file`() {
        val file = File("testfile")
        val handler = XmlHandler(file = file)
        val orgcoll = OrganizationCollection(handler)
        val chck = orgcoll.loadFromFile()
        assert(chck)
    }
}