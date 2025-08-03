package id.application.domain.repository.auth

import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.LoginResponse
import id.application.domain.model.auth.RegisterResponse

interface AuthRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: LoginRequest): RegisterResponse
}

