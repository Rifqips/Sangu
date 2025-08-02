package id.application.sangugue.data.model.auth

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

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