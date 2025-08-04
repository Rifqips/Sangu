package id.application.data.remote.model.auth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseLoginItem(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)


@Keep
data class Data(
    @SerializedName("access_token")
    val accessToken: String
)