package id.application.domain.repository

import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.LoginResponse
import id.application.domain.model.auth.RegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): BasicResponse
}

