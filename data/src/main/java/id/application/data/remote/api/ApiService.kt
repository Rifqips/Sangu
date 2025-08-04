package id.application.data.remote.api

import id.application.data.remote.model.auth.RequestLoginItem
import id.application.data.remote.model.auth.RequestRegisterItem
import id.application.data.remote.model.auth.ResponseLoginItem
import id.application.data.remote.model.basic.ResponseBasicItem
import id.application.data.remote.model.category.ResponseAllCategoryItem
import id.application.data.remote.model.transaction.RequestTransactionItem
import id.application.data.remote.model.transaction.ResponseTransactionHistoryItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(
        @Body request : RequestLoginItem
    ): Response<ResponseLoginItem>

    @POST("auth/register")
    suspend fun registerUser(
        @Body request : RequestRegisterItem
    ): Response<ResponseBasicItem>

    @GET("categories")
    suspend fun getCategories(): Response<ResponseAllCategoryItem>

    @GET("transactions")
    suspend fun getTransactions(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<ResponseTransactionHistoryItem>

    @POST("transactions")
    suspend fun createTransactions(
        @Body request : RequestTransactionItem
    ): Response<ResponseBasicItem>


}
