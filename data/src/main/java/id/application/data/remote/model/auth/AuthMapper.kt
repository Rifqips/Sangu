package id.application.data.remote.model.auth

import id.application.domain.model.auth.*

fun LoginRequest.toDto(): RequestLoginItem {
    return RequestLoginItem(email = email, password = password)
}

fun ResponseLoginItem.toDomain(): LoginResponse {
    return LoginResponse(
        token = data.accessToken,
        message = message,
        success = success
    )
}

fun ResponseRegisterItem.toDomain(): RegisterResponse {
    return RegisterResponse(
        message = message,
        success = success
    )
}
