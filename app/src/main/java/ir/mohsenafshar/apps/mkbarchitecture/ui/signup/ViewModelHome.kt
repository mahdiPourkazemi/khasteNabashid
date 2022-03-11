package ir.mohsenafshar.apps.mkbarchitecture.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import retrofit2.Call
import retrofit2.Response

class ViewModelHome : ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    fun createUser(user: UserReqBody) {
        val call = RetrofitApiProvider.userApi.createUser(user)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val id = response.body()
                if (id.isNullOrBlank()) {
                    _error.postValue("USER NOT FOUND")
                } else {
                    _userId.value = id.toString()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _error.postValue(t.message ?: "Unknown Error")
            }
        })
    }
}