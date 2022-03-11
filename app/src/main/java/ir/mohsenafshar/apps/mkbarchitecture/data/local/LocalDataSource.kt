package ir.mohsenafshar.apps.mkbarchitecture.data.local

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.mkbarchitecture.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.local.db.HobieDao
import ir.mohsenafshar.apps.mkbarchitecture.data.local.db.UserDao
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User


class LocalDataSource(private val userDao: UserDao,private val hobieDao: HobieDao): DataSource {
    override fun getUserList(): List<User> {
        return userDao.getAll()
    }

    override fun saveUserList(users: List<User>) {
        userDao.insertAll(*users.toTypedArray())
    }

    override fun saveHobieList(hobbies: List<Hobie>) {
       hobieDao.insert(*hobbies.toTypedArray())
    }

    override fun getAllUserWithHobbies(): LiveData<Map<User, List<Hobie>>> {
        return userDao.getAllUserWithHobbies()
    }


}