package id.application.domain.model.transaction

data class TransactionSummary(
    val balance: Int,
    val expense: Int,
    val income: Int
)

data class TransactionHistoryData(
    val detailsJson: String?,
    val summary: TransactionSummary
)
