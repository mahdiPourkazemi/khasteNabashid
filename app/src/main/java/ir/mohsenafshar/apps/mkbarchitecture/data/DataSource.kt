package ir.mohsenafshar.apps.mkbarchitecture.data

import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User

interface DataSource {
    fun getUserList(): List<User>
    fun saveUserList(users: List<User>)
    fun saveHobieList(hobbies:List<Hobie>)
}