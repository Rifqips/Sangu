package id.application.domain.usecase.auth

import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.LoginResponse
import id.application.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: LoginRequest): LoginResponse {
        return repository.login(request)
    }
}