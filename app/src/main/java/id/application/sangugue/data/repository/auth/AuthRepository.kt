package id.application.sangugue.data.repository.auth

import id.application.sangugue.data.model.auth.RequestLoginItem
import id.application.sangugue.data.model.auth.ResponseLoginItem
import id.application.sangugue.data.remote.ApiService
import retrofit2.Response

interface AuthRepository {
    suspend fun login(request: RequestLoginItem): Response<ResponseLoginItem>
    suspend fun register(request: RequestLoginItem): Response<ResponseLoginItem>
}

class AuthRepositoryImpl(private val api: ApiService) : AuthRepository {
    override suspend fun login(request: RequestLoginItem) = api.loginUser(request)
    override suspend fun register(request: RequestLoginItem) = api.registerUser(request)
}
