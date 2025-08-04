package id.application.sangugue.presentation.viewmodel.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.appliation.core.utils.UiState
import id.application.domain.model.category.Category
import id.application.domain.usecase.category.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoryState = MutableStateFlow<UiState<List<Category>>>(UiState.Idle)
    val categoryState: StateFlow<UiState<List<Category>>> = _categoryState

    fun fetchCategories() {
        _categoryState.value = UiState.Loading
        viewModelScope.launch {
            val result = getCategoriesUseCase()
            _categoryState.value = result.fold(
                onSuccess = { data ->
                    UiState.Success(data = data, message = "Berhasil memuat kategori")
                },
                onFailure = { error ->
                    UiState.Error(error.message ?: "Terjadi kesalahan")
                }
            )
        }
    }

    fun resetState() {
        _categoryState.value = UiState.Idle
    }
}
