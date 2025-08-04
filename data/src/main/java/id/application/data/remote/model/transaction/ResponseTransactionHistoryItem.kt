package id.application.data.remote.model.transaction


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseTransactionHistoryItem(
    @SerializedName("data")
    val `data`: DataTransactionHistory,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)


@Keep
data class DataTransactionHistory(
	@SerializedName("details")
	val details: Any,
	@SerializedName("summary")
	val summary: SummaryTransactionHistory
)


@Keep
data class SummaryTransactionHistory(
	@SerializedName("balance")
	val balance: Int,
	@SerializedName("expense")
	val expense: Int,
	@SerializedName("income")
	val income: Int
)