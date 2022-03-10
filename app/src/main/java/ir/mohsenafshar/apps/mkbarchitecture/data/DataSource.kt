package ir.mohsenafshar.apps.mkbarchitecture.data

import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserResponse

interface DataSource {
    fun getUserList(cb: NetworkCallback<List<UserResponse>>)
}