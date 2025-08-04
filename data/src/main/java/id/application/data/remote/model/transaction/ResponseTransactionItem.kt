package id.application.data.remote.model.transaction


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseTransactionItem(
    @SerializedName("data")
    val `data`: DataTransactionItem? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
)


@Keep
data class DataTransactionItem(
    @SerializedName("details")
    val details: List<DetailTransactionItem?>? = null,
    @SerializedName("summary")
    val summary: SummaryTransactionItem? = null
)


@Keep
data class DetailTransactionItem(
    @SerializedName("amount")
    val amount: Int? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("transaction_date")
    val transactionDate: String? = null,
    @SerializedName("type")
    val type: String? = null
)

@Keep
data class SummaryTransactionItem(
    @SerializedName("balance")
    val balance: Int? = null,
    @SerializedName("expense")
    val expense: Int? = null,
    @SerializedName("income")
    val income: Int? = null
)