package id.application.data.remote.model.category

import id.application.domain.model.category.Category

fun DataCategoryItem.toDomain(): Category? {
    if (id == null || name == null) {
        return null
    }
    return Category(
        id = id,
        name = name
    )
}

fun ResponseAllCategoryItem.toDomainCategories(): List<Category> {
    if (this.data == null) {
        return emptyList()
    }
    return this.data
        .mapNotNull { dataCategoryItem -> dataCategoryItem.toDomain() }
}

 data class CategoriesDomainResponse(
    val categories: List<Category>,
    val message: String,
    val isSuccess: Boolean
 )

 fun ResponseAllCategoryItem.toDomainResponse(): CategoriesDomainResponse {
    return CategoriesDomainResponse(
        categories = this.data?.mapNotNull { it.toDomain() } ?: emptyList(),
        message = this.message ?: "No message",
        isSuccess = this.success ?: false
    )
 }