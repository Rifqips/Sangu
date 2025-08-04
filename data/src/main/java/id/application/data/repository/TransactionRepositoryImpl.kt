package id.application.data.repository

import id.application.data.remote.api.ApiService
import id.application.data.remote.model.basic.toDomain
import id.application.data.remote.model.transaction.toDto
import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TransactionRepository{

    override suspend fun createTransactions(request: ItemRequestTransaction): BasicResponse {
        val response = api.createTransactions(request.toDto())
        val body = response.body()
        return body?.toDomain()
            ?: throw Exception("Register response body is null")

    }

}