package id.application.data.repository

import id.application.data.remote.api.ApiService
import id.application.data.remote.model.auth.toDomain
import id.application.data.remote.model.auth.toDto
import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.LoginResponse
import id.application.domain.model.auth.RegisterResponse
import id.application.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {
    override suspend fun login(request: LoginRequest): LoginResponse {
        val response = api.loginUser(request.toDto())
        val body = response.body()
        return body?.toDomain()
            ?: throw Exception("Login response body is null")
    }

    override suspend fun register(request: LoginRequest): RegisterResponse {
        val response = api.registerUser(request.toDto())
        val body = response.body()
        return body?.toDomain()
            ?: throw Exception("Register response body is null")
    }

}
