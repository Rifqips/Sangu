package id.application.domain.model.transaction

import androidx.annotation.Keep

data class ItemRequestTransaction(
    val amount: Int? = null,
    val category: String? = null,
    val description: String? = null,
    val transactionDate: String? = null,
    val type: String? = null
)


data class ItemResponseTransaction(
    val `data`: ItemDataTransaction? = null,
    val message: String? = null,
    val success: Boolean? = null
)

data class ItemDataTransaction(
    val details: List<ItemDetailTransaction?>? = null,
    val summary: ItemSummaryTransaction? = null
)


data class ItemDetailTransaction(
    val amount: Int? = null,
    val category: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val transactionDate: String? = null,
    val type: String? = null
)

data class ItemSummaryTransaction(
    val balance: Int? = null,
    val expense: Int? = null,
    val income: Int? = null
)
