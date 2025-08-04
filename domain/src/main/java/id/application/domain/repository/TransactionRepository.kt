package id.application.domain.repository

import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.model.transaction.ItemResponseTransaction

interface TransactionRepository {
    suspend fun createTransactions(
        request : ItemRequestTransaction
    ): BasicResponse

    suspend fun getTransactions(
        startDate: String,
        endDate: String
    ): ItemResponseTransaction
}