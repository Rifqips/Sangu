package id.application.sangu.data.remote

import id.application.sangu.data.model.UserDto
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun loginUser(): List<UserDto>

}
