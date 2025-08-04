package id.application.domain.repository

import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.transaction.ItemRequestTransaction

interface TransactionRepository {
    suspend fun createTransactions(
        request : ItemRequestTransaction
    ): BasicResponse
}