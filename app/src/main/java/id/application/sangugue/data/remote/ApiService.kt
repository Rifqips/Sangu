package id.application.sangugue.data.remote

import id.application.sangugue.data.model.auth.RequestLoginItem
import id.application.sangugue.data.model.auth.ResponseLoginItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(
        @Body request : RequestLoginItem
    ): Response<ResponseLoginItem>

    @POST("auth/register")
    suspend fun registerUser(
        @Body request : RequestLoginItem
    ): Response<ResponseLoginItem>

}
