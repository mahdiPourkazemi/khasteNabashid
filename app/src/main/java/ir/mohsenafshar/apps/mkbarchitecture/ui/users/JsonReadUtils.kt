package ir.mohsenafshar.apps.mkbarchitecture.ui.users

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
fun readFromAsset(context:Context):List<UserResponse>{
    val gson = Gson()
    val stringData = context.resources.assets.open("user.json")
        .bufferedReader().use { it.readText() }
    val type = TypeToken.getParameterized(ArrayList::class.java,
        UserResponse::class.java).type
    val userList: List<UserResponse> = gson.fromJson(stringData, type)
    Log.d("main","$userList")
    return userList
}
