package ir.mohsenafshar.apps.mkbarchitecture.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserResponse

class UserRepository(private val remoteDataSource: DataSource) {

    companion object {
        const val TAG = "Repository"
    }

    fun getUserList(): LiveData<List<UserResponse>>{
        val liveData = MutableLiveData<List<UserResponse>>()

        val cb = object : NetworkCallback<List<UserResponse>> {
            override fun onResponse(data: List<UserResponse>) {
                liveData.postValue(data)
            }

            override fun onFailure(t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        }
        remoteDataSource.getUserList(cb)
        return liveData
    }

}