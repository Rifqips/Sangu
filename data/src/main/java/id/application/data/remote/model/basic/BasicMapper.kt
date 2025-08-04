package id.application.data.remote.model.basic

import id.application.domain.model.auth.BasicResponse

fun ResponseBasicItem.toDomain(): BasicResponse {
    return BasicResponse(
        message = message,
        success = success
    )
}