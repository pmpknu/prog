package lab7.server.collection.databasehandler

import com.zaxxer.hikari.HikariDataSource
import lab7.server.collection.databasehandler.dao.AddressDAO
import lab7.server.collection.databasehandler.dao.LocationDAO
import lab7.server.collection.databasehandler.dao.OrganizationDAO
import lab7.shared.entities.organization.*
import java.sql.Connection

class PostgresHandler(private val driver: HikariDataSource) : CollectionDataBaseHandler {

    override fun loadCollection(): Set<Organization> {
        return driver.connection.use { it ->
            val query = it.prepareStatement(
                """
                SELECT * FROM organization LEFT JOIN coordinates c on organization.coordinates_id = c.id;
            """.trimIndent()
            )
            val resSet = query.executeQuery()
            val orgs = mutableSetOf<Organization>()
            while (resSet.next()) {
                val coordinates = Coordinates(
                    resSet.getFloat("x"),
                    resSet.getDouble("y")
                )
                val org =
                    OrganizationDAO(
                        resSet.getInt("id"),
                        resSet.getString("name"),
                        resSet.getTimestamp("creation_date").toLocalDateTime(),
                        OrganizationType.COMMERCIAL.valueOfOrNull(resSet.getString("org_type")),
                        resSet.getLong("annual_turnover"),
                        resSet.getInt("address_id").let { adrId ->
                            if (adrId > 0) {
                                adrId
                            } else {
                                null
                            }
                        },
                        resSet.getInt("author_id")
                    )
                val addressDao = org.postalAddressId?.let { it1 -> fetchAddressId(it1) }
                if (addressDao != null) {
                    val locationDao = fetchLocation(addressDao.locationId)
                    val address = Address(
                        addressDao.street,
                        Location(locationDao.x, locationDao.y, locationDao.name)
                    )
                    orgs.add(
                        Organization(
                            org.id,
                            org.name,
                            coordinates,
                            org.creationDate.toLocalDate(),
                            org.annualTurnover,
                            org.type,
                            address,
                            org.authorId,
                        )
                    )
                } else {
                    orgs.add(
                        Organization(
                            org.id,
                            org.name,
                            coordinates,
                            org.creationDate.toLocalDate(),
                            org.annualTurnover,
                            org.type,
                            null,
                            org.authorId,
                        )
                    )
                }
            }
            orgs.forEach {
                println(it)
            }
            orgs
        }
    }

    private fun fetchLocation(locationId: Int): LocationDAO {
        return driver.connection.use {
            val statement = it.prepareStatement("SELECT * FROM location WHERE id =?")
            statement.setInt(1, locationId)
            val res = statement.executeQuery()
            res.next()
            LocationDAO(
                locationId,
                res.getLong("x"),
                res.getInt("y"),
                res.getString("name"),
            )
        }
    }

    private fun fetchAddressId(addressId: Int): AddressDAO? {
        return driver.connection.use {
            val statement = it.prepareStatement("SELECT * FROM address WHERE id = ?")
            statement.setInt(1, addressId)
            val res = statement.executeQuery()
            if (!res.next()) {
                return null
            }
            AddressDAO(
                addressId,
                res.getString("street"),
                res.getInt("location_id")
            )
        }
    }

    override fun add(item: Organization): Int {
        val id = driver.connection.use {
            it.autoCommit = false
            val coordinatesId = insertCoordinates(it, item.coordinates)
            val addressId: Int? = item.postalAddress?.let { it1 -> insertAddress(it, it1) }
            val insertOrganizationStatement =
                it.prepareStatement("INSERT INTO organization(name, coordinates_id, annual_turnover, org_type, address_id, author_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING ID")
            insertOrganizationStatement.setString(1, item.name)
            insertOrganizationStatement.setInt(2, coordinatesId)
            insertOrganizationStatement.setLong(3, item.annualTurnover)
            insertOrganizationStatement.setString(4, item.type.toString())
            if (addressId != null) {
                insertOrganizationStatement.setInt(5, addressId)
            } else {
                insertOrganizationStatement.setObject(5, null)
            }
            insertOrganizationStatement.setInt(6, item.authorId)
            addressId?.let { it1 -> insertOrganizationStatement.setInt(5, it1) }

            val result = insertOrganizationStatement.executeQuery()
            if (!result.next()) {
                it.rollback()
                throw Exception("insert organization exception")
            }
            val id = result.getInt("id")
            it.commit()
            id
        }
        return id
    }

    /**
     * Вставляет координаты в базу данных и возвращает ID
     * @param it
     * @param item
     */
    private fun insertCoordinates(it: Connection, item: Coordinates): Int {
        val insertCoordinatesStm = it.prepareStatement("INSERT INTO COORDINATES(X, Y) VALUES (?, ?) RETURNING ID")
        insertCoordinatesStm.setFloat(1, item.x)
        insertCoordinatesStm.setDouble(2, item.y)
        val coordinatesResult = insertCoordinatesStm.executeQuery()
        if (!coordinatesResult.next()) {
            it.rollback()
            throw Exception("insert coords exception")
        }
        return coordinatesResult.getInt("id")
    }

    private fun insertLocation(it: Connection, location: Location): Int {
        val insertLocationStatement =
            it.prepareStatement("INSERT INTO LOCATION(X, Y, NAME) VALUES (?, ?, ?) RETURNING ID")
        insertLocationStatement.setInt(1, location.x)
        insertLocationStatement.setLong(2, location.y)
        insertLocationStatement.setString(3, location.name)
        val locationResult = insertLocationStatement.executeQuery()
        if (!locationResult.next()) {
            it.rollback()
            throw Exception("insert location exception")
        }
        return locationResult.getInt("id")
    }

    private fun insertAddress(it: Connection, address: Address): Int {
        val locationId = insertLocation(it, address.town)
        val insertAddressStatement =
            it.prepareStatement("INSERT INTO address(street, location_id) VALUES (?, ?) RETURNING ID")
        insertAddressStatement.setString(1, address.street)
        insertAddressStatement.setInt(2, locationId)
        val locationResult = insertAddressStatement.executeQuery()
        if (!locationResult.next()) {
            it.rollback()
            throw Exception("insert address exception")
        }
        return locationResult.getInt("id")
    }

    override fun delete(id: Int, authorId: Int): Boolean {
        val isDeleted = driver.connection.use {
            it.autoCommit = false

            val deleteOrganizationStatement =
                it.prepareStatement("DELETE FROM organization WHERE id = ? AND author_id = ?")
            deleteOrganizationStatement.setInt(1, id)
            deleteOrganizationStatement.setInt(2, authorId)

            val result = (deleteOrganizationStatement.executeUpdate() > 0)
            if (!result) {
                it.rollback()
                throw Exception("delete organization exception")
            }
            it.commit()
            result
        }
        return isDeleted
    }

    override fun removeAll(authorId: Int): Boolean {
        val isDeleted = driver.connection.use {
            it.autoCommit = false

            val deleteOrganizationsStatement =
                it.prepareStatement("DELETE FROM organization WHERE author_id = ?")
            deleteOrganizationsStatement.setInt(1, authorId)

            val result = (deleteOrganizationsStatement.executeUpdate() > 0)
            if (!result) {
                it.rollback()
                throw Exception("remove organizations exception")
            }
            it.commit()
            result
        }
        return isDeleted
    }

    override fun update(item: Organization, authorId: Int): Boolean {
        val isUpdated = driver.connection.use {
            it.autoCommit = false

            val coordinatesId = insertCoordinates(it, item.coordinates)
            val addressId: Int? = item.postalAddress?.let { it1 -> insertAddress(it, it1) }

            val updateOrganizationStatement =
                it.prepareStatement("""
                    UPDATE organization
                    SET name = ?, coordinates_id = ?, annual_turnover = ?, org_type = ?, address_id = ?
                    WHERE id = ? AND author_id = ?
                """.trimIndent())

            updateOrganizationStatement.setString(1, item.name)
            updateOrganizationStatement.setInt(2, coordinatesId)
            updateOrganizationStatement.setLong(3, item.annualTurnover)
            updateOrganizationStatement.setString(4, item.type.toString())
            if (addressId != null) {
                updateOrganizationStatement.setInt(5, addressId)
            } else {
                updateOrganizationStatement.setObject(5, null)
            }

            updateOrganizationStatement.setInt(6, item.id)
            updateOrganizationStatement.setInt(7, authorId)

            val result = (updateOrganizationStatement.executeUpdate() > 0)
            if (!result) {
                it.rollback()
                throw Exception("remove organizations exception")
            }
            it.commit()
            result
        }
        return isUpdated
    }

}

inline fun <reified T : Enum<T>> T.valueOfOrNull(value: String): T? {
    if (value == "null") {
        return null
    }
    return enumValueOf<T>(value)
}