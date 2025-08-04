package id.application.sangugue.presentation.viewmodel.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.appliation.core.utils.UiState
import id.application.domain.model.auth.BasicResponse
import id.application.domain.model.transaction.ItemRequestTransaction
import id.application.domain.usecase.transaction.CreateTransactionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val createTransactionUseCase: CreateTransactionUseCase
): ViewModel(){

    var transactionState by mutableStateOf<UiState<BasicResponse>>(UiState.Idle)
        private set

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

    fun resetState() {
        transactionState = UiState.Idle
    }
}