package id.application.data.repository

import id.application.data.remote.api.ApiService
import id.application.data.remote.model.category.toDomainCategories
import id.application.domain.model.category.Category
import id.application.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CategoryRepository {

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = apiService.getCategories()

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    val categories = responseBody.toDomainCategories()
                    Result.success(categories)
                } else {
                    Result.failure(Exception("API response body is null but successful"))
                }
            } else {
                Result.failure(Exception("API request failed with code: ${response.code()}, message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}