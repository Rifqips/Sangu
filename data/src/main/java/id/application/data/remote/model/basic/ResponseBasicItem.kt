package id.application.data.remote.model.basic

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseBasicItem(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)