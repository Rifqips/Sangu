package id.application.sangugue.data.model.auth

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseRegisterItem(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)