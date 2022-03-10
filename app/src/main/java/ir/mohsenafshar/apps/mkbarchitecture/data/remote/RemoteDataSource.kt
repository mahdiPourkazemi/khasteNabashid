package ir.mohsenafshar.apps.mkbarchitecture.data.remote

import ir.mohsenafshar.apps.mkbarchitecture.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.NetworkCallback
import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val userApi: UserApi): DataSource {

    override fun getUserList(cb: NetworkCallback<List<UserResponse>>){

        userApi.getUserList().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                cb.onResponse(response.body() ?: emptyList())
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }
}