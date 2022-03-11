package ir.mohsenafshar.apps.mkbarchitecture.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _listUser = MutableLiveData<List<String>>()
    val listUsers: LiveData<List<String>> = _listUser
    private val _userWithHobbiesLiveData = MutableLiveData<List<UserResponse>>()
    val userWithHobbiesLiveData: LiveData<List<UserResponse>> = _userWithHobbiesLiveData

    private val _searchResult = MutableLiveData<List<String>>()
    val searchResult: LiveData<List<String>> = _searchResult

    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUserList()
    }

    fun saveHobie(hobieList: List<Hobie>) {
        userRepository.saveHobieList(hobieList)
    }

    fun saveUserList(userList: List<User>) {
        userRepository.saveUserList(userList)
    }

    private fun searchFromUsers(query: HashMap<String, String>) {
        RetrofitApiProvider.userApi.getUserList(query)
            .enqueue(object : Callback<List<UserResponse>?> {
                override fun onResponse(
                    call: Call<List<UserResponse>?>,
                    response: Response<List<UserResponse>?>
                ) {
                    _searchResult.postValue(response.body()?.map {
                        it.firstName
                    })
                }

                override fun onFailure(call: Call<List<UserResponse>?>, t: Throwable) {
                    Log.d("TAG", "searchResult:" + t.message.toString())
                }
            })
    }

    fun getUserFromFirstName(firstname: String) {
        if (firstname.isNotBlank()) {
            searchFromUsers(hashMapOf("firstName" to firstname))
        }
    }

    fun getAllUserWithHobbies():LiveData<List<UserResponse>> {
        return userRepository.getAllUserWithHobbies()
    }
}