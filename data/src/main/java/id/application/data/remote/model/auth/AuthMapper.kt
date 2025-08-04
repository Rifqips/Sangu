package id.application.data.remote.model.auth

import id.application.domain.model.auth.LoginRequest
import id.application.domain.model.auth.LoginResponse
import id.application.domain.model.auth.RegisterRequest

fun LoginRequest.toDto(): RequestLoginItem {
    return RequestLoginItem(email = email, password = password)
}
fun RegisterRequest.toDto(): RequestRegisterItem {
    return RequestRegisterItem(email = email, password = password, confirmPassword = confirmPassword)
}

fun ResponseLoginItem.toDomain(): LoginResponse {
    return LoginResponse(
        token = data.accessToken,
        message = message,
        success = success
    )
}


