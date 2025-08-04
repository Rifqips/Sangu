package id.application.data.remote.model.auth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestRegisterItem(
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)