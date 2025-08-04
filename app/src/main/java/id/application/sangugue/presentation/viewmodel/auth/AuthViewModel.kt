package id.application.sangugue.presentation.viewmodel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.appliation.core.utils.UiState
import id.application.data.local.datastore.ApplicationPreferences
import id.application.domain.model.auth.LoginRequest
import id.application.domain.repository.AuthRepository
import id.application.domain.usecase.auth.LoginUseCase
import id.application.domain.usecase.auth.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val prefs: ApplicationPreferences
) : ViewModel() {

    var authState by mutableStateOf<UiState>(UiState.Idle)
        private set

    fun loginUser(request: LoginRequest) {
        viewModelScope.launch {
            authState = UiState.Loading
            try {
                val response = loginUseCase(request)
                prefs.saveToken(response.token)
                authState = UiState.Success(response.message)
            } catch (e: Exception) {
                authState = UiState.Error("Login gagal: ${e.localizedMessage}")
            }
        }
    }

    fun registerUser(request: LoginRequest) {
        viewModelScope.launch {
            authState = UiState.Loading
            try {
                val response = registerUseCase(request)
                authState = UiState.Success(response.message)
            } catch (e: Exception) {
                authState = UiState.Error("Registrasi gagal: ${e.localizedMessage}")
            }
        }
    }

    fun resetState() {
        authState = UiState.Idle
    }
}
