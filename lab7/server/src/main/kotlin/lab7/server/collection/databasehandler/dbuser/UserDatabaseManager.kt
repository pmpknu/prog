package lab7.server.collection.databasehandler.dbuser

import com.zaxxer.hikari.HikariDataSource
import lab7.shared.entities.user.User
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import java.security.MessageDigest

class UserDatabaseManager(private val ds: HikariDataSource): UserManager {
    private val md = MessageDigest.getInstance("SHA-1")
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun checkUser(user: User): User? {
        logger.info(user.toString())
        val dbUser: User? = getUserByName(user.name)
        if(dbUser == null) {
            logger.info("No user in database")
            val newUser: User = createNewUser(user)
            return newUser
        }
        return if(hashPassword(user.password) == dbUser.password) {
            logger.warn("Password do  match")
            dbUser
        } else {
            logger.warn("Password do not match")
            null
        }
    }

    private fun getUserByName(name: String): User? {
        return ds.connection.use {
            logger.info("Getting all users with name = '${name}'")
            val st = it.prepareStatement("SELECT * FROM users WHERE name = ?")
            st.setString(1, name)
            val res = st.executeQuery()
            var user: User? = null
            while(res.next()) {
                user = User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("password"),
                )
            }
            user
        }
    }

    private fun createNewUser(user: User): User {
        return ds.connection.use {
            val st = it.prepareStatement("INSERT INTO USERS(name, password) VALUES(?, ?) RETURNING id")
            st.setString(1, user.name)
            st.setString(2, hashPassword(user.password))
            val res = st.executeQuery()
            res.next()
            val id = res.getInt("id")
            user.copy(id = id)
        }
    }

    private fun hashPassword(password: String): String {
        return md.digest(password.toByteArray(Charset.forName("UTF-8"))).toHex()
    }
}
fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }