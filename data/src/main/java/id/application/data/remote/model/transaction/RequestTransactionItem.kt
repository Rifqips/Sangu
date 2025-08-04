package id.application.data.remote.model.transaction


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestTransactionItem(
    @SerializedName("amount")
    val amount: Int? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("transaction_date")
    val transactionDate: String? = null,
    @SerializedName("type")
    val type: String? = null
)