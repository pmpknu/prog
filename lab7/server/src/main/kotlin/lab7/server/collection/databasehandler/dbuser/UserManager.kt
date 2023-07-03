package lab7.server.collection.databasehandler.dbuser

import lab7.shared.entities.user.User

interface UserManager {

    /**
     * Проверяет есть ли пользователь с таким именем и паролем в базе данных.
     * Если он есть, проверяет, совпадают ли пароли.
     * Если пароли совпадают, возвращает пользователя.
     * Если нет -- возвращает null
     * Если его нет, возвращает новосозданного пользователя
     *
     * @param user
     * @return
     */
    fun checkUser(user: User): User?
}