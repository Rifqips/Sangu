package id.application.domain.usecase.auth

import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.auth.RegisterRequest
import id.application.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): BasicResponse {
        return repository.register(request)
    }
}