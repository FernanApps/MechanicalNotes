package presentation

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import domain.model.User
import domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class LoginUiState(
    val email: String = "",
    val password: String = ""
)


class LoginViewModel(
    private val authService: AuthRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _emailError = MutableStateFlow(false)
    val emailError = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow(false)
    val passwordError = _passwordError.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing = _isProcessing.asStateFlow()

    val isButtonEnabled: StateFlow<Boolean> = combine(uiState) { states ->
        val state = states.first()
        state.email.isNotBlank() && isCorrectEmail(state.email) && state.password.isNotBlank()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), false
    )

    init {


    }

    fun checkCurrentUser(){
        launchWithCatchingException {
            authService.currentUser.collect {
                _currentUser.value = it
            }
        }
        println("isAuthenticated " + authService.isAuthenticated)

    }

    private fun isCorrectEmail(email: String): Boolean{
        val emailPattern = """^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$"""
        return emailPattern.toRegex().matches(email)
    }


    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue) }
        //reset error
        if (newValue.isNotBlank()) _emailError.value = false
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
        //reset error
        if (newValue.isNotBlank()) _passwordError.value = false
    }

    fun onSignInClick() {

        if (_uiState.value.email.isEmpty()) {
            _emailError.value = true
            return
        }

        if (_uiState.value.password.isEmpty()) {
            _emailError.value = true
            return
        }

        viewModelScope.launch {
            _isProcessing.value = true
            //val result = authService.createUser(_uiState.value.email, _uiState.value.password)
            try{
                authService.authenticate(_uiState.value.email, _uiState.value.password)
                //_isProcessing.value = false
            } catch (e: Exception){
                println(e.message)
                e.printStackTrace()
                _emailError.value = true
                _passwordError.value = true
            } finally {
                _isProcessing.value = false

            }

        }

    }


    fun onSignOut() {
        launchWithCatchingException {
            authService.signOut()
        }
    }

}

