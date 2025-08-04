package id.application.domain.usecase.auth

import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.RegisterResponse
import id.application.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: LoginRequest): RegisterResponse {
        return repository.register(request)
    }
}