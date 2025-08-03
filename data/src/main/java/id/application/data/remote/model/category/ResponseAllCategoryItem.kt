package id.application.data.remote.model.category


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseAllCategoryItem(
    @SerializedName("data")
    val `data`: List<DataCategoryItem>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)


@Keep
data class DataCategoryItem(
	@SerializedName("ID")
	val id: Int,
	@SerializedName("Name")
	val name: String
)