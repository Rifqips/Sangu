package id.application.sangugue.presentation.viewmodel.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.appliation.core.utils.UiState
import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.category.Category
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.model.transaction.ItemResponseTransaction
import id.application.domain.usecase.transaction.CreateTransactionUseCase
import id.application.domain.usecase.transaction.GetTransactionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
): ViewModel(){

    var transactionState by mutableStateOf<UiState<BasicResponse>>(UiState.Idle)
        private set

    private val _getTransactionState = MutableStateFlow<UiState<ItemResponseTransaction>>(UiState.Loading)
    val getTransactionState: StateFlow<UiState<ItemResponseTransaction>> = _getTransactionState.asStateFlow()


    fun createTransaction(request : ItemRequestTransaction){
        viewModelScope.launch {
            transactionState = UiState.Loading
            try {
                val response = createTransactionUseCase.invoke(request)
                transactionState = UiState.Success(
                    data = response,
                    message = response.message,
                    success = response.success
                )

            }catch (e : Exception){
                transactionState = UiState.Error("Gagal Membuat Transaksi: ${e.localizedMessage}")
            }
        }
    }

    fun getTransactions(startDate: String, endDate: String) {
        viewModelScope.launch {
            _getTransactionState.value = UiState.Loading
            try {
                val result = getTransactionUseCase.invoke(startDate, endDate)
                _getTransactionState.value = UiState.Success(result)
            } catch (e: Exception) {
                _getTransactionState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun resetState() {
        transactionState = UiState.Idle
        _getTransactionState.value = UiState.Loading
    }
}