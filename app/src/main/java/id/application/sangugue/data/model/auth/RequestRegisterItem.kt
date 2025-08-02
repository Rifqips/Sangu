package id.application.sangugue.data.model.auth

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


@Keep
data class RequestRegisterItem(
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)