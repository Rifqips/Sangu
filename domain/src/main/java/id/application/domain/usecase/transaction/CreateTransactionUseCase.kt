package id.application.domain.usecase.transaction

import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.repository.TransactionRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke(request: ItemRequestTransaction) : BasicResponse{
        return repository.createTransactions(request)
    }
}