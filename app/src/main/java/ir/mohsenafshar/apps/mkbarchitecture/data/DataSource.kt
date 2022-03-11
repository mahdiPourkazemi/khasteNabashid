package ir.mohsenafshar.apps.mkbarchitecture.data

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User

interface DataSource {
    fun getUserList(): List<User>
    fun saveUserList(users: List<User>)
    fun saveHobieList(hobbies:List<Hobie>)
    fun getAllUserWithHobbies():LiveData<Map<User,List<Hobie>>>
}