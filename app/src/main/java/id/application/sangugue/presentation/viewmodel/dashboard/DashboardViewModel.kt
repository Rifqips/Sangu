package id.application.sangugue.presentation.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.appliation.core.utils.UiState
import id.application.data.local.datastore.ApplicationPreferences
import id.application.domain.model.transaction.ItemResponseTransaction
import id.application.domain.usecase.transaction.GetTransactionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase,
    private val prefs: ApplicationPreferences
): ViewModel(){

    private val _getTransactionState = MutableStateFlow<UiState<ItemResponseTransaction>>(UiState.Loading)
    val getTransactionState: StateFlow<UiState<ItemResponseTransaction>> = _getTransactionState.asStateFlow()

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.getEmailFlow.collect { email ->
                _userEmail.value = email
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            prefs.clearAll()
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
        _getTransactionState.value = UiState.Loading
    }
}