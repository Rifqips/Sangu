package id.application.sangugue.presentation.viewmodel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.application.sangugue.data.local.datastore.ApplicationPreferences
import id.application.sangugue.data.model.auth.RequestLoginItem
import id.application.sangugue.data.repository.auth.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val prefs : ApplicationPreferences
) : ViewModel() {

    var authState by mutableStateOf<AuthUiState>(AuthUiState.Idle)
        private set

    fun loginUser(request: RequestLoginItem) {
        viewModelScope.launch {
            authState = AuthUiState.Loading
            try {
                val response = repository.login(request)
                if (response.isSuccessful) {
                    val data = response.body()
                    val token = data?.data?.accessToken.orEmpty()
                    prefs.saveToken(token)
                    authState = AuthUiState.Success(data?.message ?: "Login berhasil")
                } else {
                    authState = AuthUiState.Error("Login gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                authState = AuthUiState.Error("Terjadi kesalahan: ${e.localizedMessage}")
            }
        }
    }

    fun registerUser(request: RequestLoginItem) {
        viewModelScope.launch {
            authState = AuthUiState.Loading
            try {
                val response = repository.register(request)
                if (response.isSuccessful) {
                    val data = response.body()
                    authState = AuthUiState.Success(data?.message ?: "Registrasi berhasil")
                } else {
                    authState = AuthUiState.Error("Registrasi gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                authState = AuthUiState.Error("Terjadi kesalahan: ${e.localizedMessage}")
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
