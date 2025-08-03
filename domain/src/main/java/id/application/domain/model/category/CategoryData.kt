package id.application.domain.model.category

data class Category(
    val id: Int,
    val name: String
)

data class CategoryList(
    val categories: List<Category>,
    val message: String,
    val isSuccess: Boolean
)