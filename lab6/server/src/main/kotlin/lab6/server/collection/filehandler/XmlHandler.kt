package lab6.server.collection.filehandler

import lab6.shared.entities.organization.LocalDateAdapter
import lab6.shared.entities.organization.Organization
import lab6.shared.entities.organization.OrganizationWrapper
import lab6.shared.entities.organization.OrganizationsSet
import java.io.*
import java.util.HashSet
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Marshaller
import javax.xml.transform.stream.StreamSource

class XmlHandler(private val file: File?) : CollectionFileHandler {
    override fun loadCollection(): HashSet<Organization>? {
        if (file == null || !file.exists())
            return null

        val bufferedReader = BufferedReader(FileReader(file))
        val xmlContent = bufferedReader.readText()

        try {
            val context = JAXBContext.newInstance(OrganizationsSet::class.java, HashSet::class.java)
            val unmarshaller = context.createUnmarshaller()

            val stringReader = StringReader(xmlContent)
            val organizationsSet = unmarshaller.unmarshal(StreamSource(stringReader), OrganizationsSet::class.java).value

            val organizations = HashSet<Organization>()
            organizationsSet.organization.forEach { organizations.add(it.returnOranization()) }
            return organizations
        } catch (e: JAXBException) {
            println("Error: failed to unmarshal file")
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            println("Error: I/O failed")
            e.printStackTrace()
            return null
        }
        finally {
            bufferedReader.close()
        }
    }

    override fun saveCollection(coll: HashSet<Organization>) : Boolean {
        if (file == null || !file.exists())
            return false

        var organizationsSet = OrganizationsSet()
        coll.forEach { organizationsSet.organization.add(OrganizationWrapper(it)) }

        try {
            val context: JAXBContext  = JAXBContext.newInstance(OrganizationsSet::class.java, HashSet::class.java)
            val marshaller: Marshaller = context.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            marshaller.setAdapter(LocalDateAdapter())

            val writer = FileWriter(file)
            marshaller.marshal(organizationsSet, writer)
            writer.close()

            return true
        } catch (e: JAXBException) {
            println("Error: failed to marshal object to file")
            e.printStackTrace()
            return false
        }
    }

    override fun canWrite(): Boolean {
        if (file == null)
            return false
        return file.canWrite()
    }

    override fun canRead(): Boolean {
        if (file == null)
            return false
        return file.canRead()
    }

    /*fun saveToFile(coll: OrganizationCollection) : Boolean {
        if (file == null)
            return false

        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        val docBuilder: DocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc: Document = docBuilder.newDocument()
        val writer = FileWriter(file)

        val mainRootElement: Element = doc.createElement("Organizations")

        for (organization in coll) {
            val rootElement: Element = doc.createElement("Organization")
            rootElement.setAttribute("id", organization.id.toString())

            val name: Element =  doc.createElement("name")
            name.appendChild(doc.createTextNode(organization.name))
            rootElement.appendChild(name)

            val coordinates: Element = doc.createElement("coordinates")
            val xCoord: Element = doc.createElement("x")
            val yCoord: Element = doc.createElement("y")
            xCoord.appendChild(doc.createTextNode(organization.coordinates.x.toString()))
            yCoord.appendChild(doc.createTextNode(organization.coordinates.y.toString()))
            coordinates.appendChild(xCoord)
            coordinates.appendChild(yCoord)
            rootElement.appendChild(coordinates)

            val creationDate: Element = doc.createElement("creationDate")
            creationDate.appendChild(doc.createTextNode(organization.creationDate.toString()))
            rootElement.appendChild(creationDate)

            val annualTurnover: Element = doc.createElement("annualTurnover")
            annualTurnover.appendChild(doc.createTextNode(organization.annualTurnover.toString()))
            rootElement.appendChild(annualTurnover)

            if (organization.type != null) {
                val type: Element = doc.createElement("type")
                type.appendChild(doc.createTextNode(organization.type.toString()))
                rootElement.appendChild(type)
            }

            if (organization.postalAddress != null) {
                val postalAddress: Element = doc.createElement("postalAddress")
                val street: Element = doc.createElement("street")
                val town: Element = doc.createElement("town")
                val xTown: Element = doc.createElement("x")
                val yTown: Element = doc.createElement("y")
                val nameTown: Element = doc.createElement("name")
                xTown.appendChild(doc.createTextNode(organization.postalAddress.town.x.toString()))
                yTown.appendChild(doc.createTextNode(organization.postalAddress.town.y.toString()))
                nameTown.appendChild(doc.createTextNode(organization.postalAddress.town.name))
                town.appendChild(xTown)
                town.appendChild(yTown)
                town.appendChild(nameTown)
                street.appendChild(doc.createTextNode(organization.postalAddress.street))
                postalAddress.appendChild(street)
                postalAddress.appendChild(town)
                rootElement.appendChild(postalAddress)
            }

            mainRootElement.appendChild(rootElement)
        }
        doc.appendChild(mainRootElement)

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")

        transformer.transform(DOMSource(doc), StreamResult(writer))
        writer.flush()
        writer.close()

    }*/
}
