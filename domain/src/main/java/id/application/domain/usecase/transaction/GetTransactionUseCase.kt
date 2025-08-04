package id.application.domain.usecase.transaction

import id.application.domain.model.transaction.ItemResponseTransaction
import id.application.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke(startDate: String, endDate: String) : ItemResponseTransaction {
        return repository.getTransactions(startDate, endDate)
    }
}