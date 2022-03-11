package ir.mohsenafshar.apps.mkbarchitecture.data.local

import ir.mohsenafshar.apps.mkbarchitecture.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.local.db.UserDao
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User


class LocalDataSource(private val userDao: UserDao): DataSource {
    override fun getUserList(): List<User> {
        return userDao.getAll()
    }

    override fun saveUserList(users: List<User>) {
        userDao.insertAll(*users.toTypedArray())
    }
}