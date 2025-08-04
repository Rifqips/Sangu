package id.application.data.remote.model.transaction

import id.application.domain.model.transaction.ItemRequestTransaction

fun ItemRequestTransaction.toDto(): RequestTransactionItem {
    return RequestTransactionItem(
        amount = amount,
        category = category,
        description = description,
        transactionDate = transactionDate,
        type = type
    )
}