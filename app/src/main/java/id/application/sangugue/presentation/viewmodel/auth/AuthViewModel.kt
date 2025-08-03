package id.application.sangugue.presentation.viewmodel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.application.data.local.datastore.ApplicationPreferences
import id.application.domain.model.auth.LoginRequest
import id.application.domain.repository.auth.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: ApplicationPreferences
) : ViewModel() {

    var authState by mutableStateOf<AuthUiState>(AuthUiState.Idle)
        private set

    fun loginUser(request: LoginRequest) {
        viewModelScope.launch {
            authState = AuthUiState.Loading
            try {
                val response = repository.login(request)
                prefs.saveToken(response.token)
                authState = AuthUiState.Success(response.message)
            } catch (e: Exception) {
                authState = AuthUiState.Error("Login gagal: ${e.localizedMessage}")
            }
        }
    }

    fun registerUser(request: LoginRequest) {
        viewModelScope.launch {
            authState = AuthUiState.Loading
            try {
                val response = repository.register(request)
                authState = AuthUiState.Success(response.message)
            } catch (e: Exception) {
                authState = AuthUiState.Error("Registrasi gagal: ${e.localizedMessage}")
            }
        }
    }

    fun resetState() {
        authState = AuthUiState.Idle
    }
}


sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val message: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
