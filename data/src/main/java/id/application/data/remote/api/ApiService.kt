package id.application.data.remote.api

import id.application.data.remote.model.auth.ResponseLoginItem
import id.application.data.remote.model.auth.ResponseRegisterItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(
        @Body request : id.application.data.remote.model.auth.RequestLoginItem
    ): Response<ResponseLoginItem>

    @POST("auth/register")
    suspend fun registerUser(
        @Body request : id.application.data.remote.model.auth.RequestLoginItem
    ): Response<ResponseRegisterItem>

}
