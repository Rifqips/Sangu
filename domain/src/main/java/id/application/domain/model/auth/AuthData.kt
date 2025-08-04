package id.application.domain.model.auth


data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val message: String,
    val success: Boolean
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String
)

data class BasicResponse(
    val message: String,
    val success: Boolean
)