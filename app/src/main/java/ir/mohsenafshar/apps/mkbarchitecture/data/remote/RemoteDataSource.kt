package ir.mohsenafshar.apps.mkbarchitecture.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.mohsenafshar.apps.mkbarchitecture.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.Mapper
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.UserApi

class RemoteDataSource(private val userApi: UserApi): DataSource {

    override fun getUserList(): List<User> {
        val result: List<UserResponse> = userApi.getUserList().execute().body() ?: emptyList()
        return result.map {
            Mapper.transformToUser(it)
        }
    }

    override fun saveUserList(users: List<User>) {
        // do nothing
    }

    override fun saveHobieList(hobbies: List<Hobie>) {
      //do nothing
    }

    override fun getAllUserWithHobbies(): LiveData<Map<User, List<Hobie>>> {
        return MutableLiveData(mapOf())
    }
}