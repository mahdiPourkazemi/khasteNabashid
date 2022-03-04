package ir.mohsenafshar.apps.mkbarchitecture.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<String>>()
    val listUsers: LiveData<List<String>> = _listUser

    private val _searchResult = MutableLiveData<List<String>>()
    val searchResult: LiveData<List<String>> = _searchResult

    fun getUsersFromServer() {
        NetworkManager.userApi.getUser().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                _listUser.postValue(response.body()?.map { user ->
                    "${user.firstName} ${user.lastName}"
                })
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }

    private fun searchFromUsers(query: HashMap<String, String>) {
        NetworkManager.userApi.getUser(query).enqueue(object : Callback<List<UserResponse>?> {
            override fun onResponse(call: Call<List<UserResponse>?>, response: Response<List<UserResponse>?>) {
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
}