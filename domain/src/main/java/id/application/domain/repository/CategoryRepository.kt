package id.application.domain.repository

import id.application.domain.model.category.Category

interface CategoryRepository {

    suspend fun getCategories(): Result<List<Category>>
}