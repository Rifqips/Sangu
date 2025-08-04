package id.application.data.remote.model.transaction

import id.application.domain.model.transaction.ItemDataTransaction
import id.application.domain.model.transaction.ItemDetailTransaction
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.model.transaction.ItemResponseTransaction
import id.application.domain.model.transaction.ItemSummaryTransaction

fun ItemRequestTransaction.toDto(): RequestTransactionItem {
    return RequestTransactionItem(
        amount = amount,
        category = category,
        description = description,
        transactionDate = transactionDate,
        type = type
    )
}

fun ResponseTransactionItem.toDto(): ItemResponseTransaction {
    return ItemResponseTransaction(
        data = this.data?.toDto(),
        message = this.message,
        success = this.success
    )
}

fun DataTransactionItem.toDto(): ItemDataTransaction {
    return ItemDataTransaction(
        details = this.details?.map { it?.toDto() },
        summary = this.summary?.toDto()
    )
}


fun DetailTransactionItem.toDto(): ItemDetailTransaction {
    return ItemDetailTransaction(
        amount = this.amount,
        category = this.category,
        description = this.description,
        id = this.id,
        transactionDate = this.transactionDate,
        type = this.type
    )
}


fun SummaryTransactionItem.toDto(): ItemSummaryTransaction {
    return ItemSummaryTransaction(
        balance = this.balance,
        expense = this.expense,
        income = this.income
    )
}
