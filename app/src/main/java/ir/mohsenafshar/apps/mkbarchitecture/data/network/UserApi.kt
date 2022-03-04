package ir.mohsenafshar.apps.mkbarchitecture.data.network

import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @GET("users")
    fun getUser(@QueryMap users: HashMap<String, String> = hashMapOf()): Call<List<UserResponse>>

    @POST("users")
    fun createUser(
        @Body userReqBody: UserReqBody
    ): Call<String>

    @Multipart
    @POST("users/{id}/image")
    fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Call<Any>
}