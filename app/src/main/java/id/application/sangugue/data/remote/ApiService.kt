package id.application.sangugue.data.remote

import id.application.sangugue.data.model.auth.UserDto
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun loginUser(): List<UserDto>

}
