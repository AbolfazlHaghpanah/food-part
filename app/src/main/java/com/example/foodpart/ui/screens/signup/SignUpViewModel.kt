package com.example.foodpart.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.RegisterUser
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.ui.components.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userApi: UserApi
) : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userRegisterResult = MutableStateFlow<Result>(Result.Idle)
    val userRegisterResult = _userRegisterResult.asStateFlow()

    private val _usernameValid = MutableStateFlow<String?>(null)
    val usernameValid = _usernameValid.asStateFlow()

    private val _passwordValid = MutableStateFlow<String?>(null)
    val passwordValid = _passwordValid.asStateFlow()

    private val _repeatPasswordValid = MutableStateFlow<String?>(null)
    val repeatPasswordValid = _repeatPasswordValid.asStateFlow()

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword = _repeatPassword.asStateFlow()

    fun registerUser() {

        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    userApi.registerUser(RegisterUser(username.value, password.value))
                },
                onDataReady = {}
            ).collect(_userRegisterResult)


        }
    }

    fun setUsername(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _username.emit(value)
        }
    }

    fun setPassword(value: String) {
        viewModelScope.launch {
            _password.emit(value)
        }
    }

    fun setRepeatPassword( value: String){
        viewModelScope.launch {
            _repeatPassword.emit(value)
        }
    }


    fun checkUsernameValidation(
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            if (username.value.isEmpty()) _usernameValid.emit("نام کاربری را وارد کنید")
            else if (username.value.length < 4) {
                _usernameValid.emit("نام کاربری خیلی کوچیکه")
            } else {
                _usernameValid.emit(null)
            }
        }

    }

    fun checkPasswordValidation(
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            if (password.value.isEmpty()) _passwordValid.emit("رمز عبور را وارد کنید")
            else if (password.value.contains(Regex("[a-z]"))
                && password.value.contains(Regex("[A-Z]"))
                && password.value.length >= 8
            ) {
                _passwordValid.emit(null)
            } else {
                _passwordValid.emit("رمز عبور باید حداقل ۸ کاراکتر و شامل حروف کوچک و بزرگ باشد")
            }


        }
    }

    fun checkRepeatPasswordValidation(
        password: String,
        repeatPassword: String
    ) {
        viewModelScope.launch {
            if (repeatPassword.isEmpty()) _repeatPasswordValid.emit("رمز عبور مجدد را وارد کنید")
            else if (repeatPassword != password) _repeatPasswordValid.emit("رمز عبور با رمز عبور مجدد یکسان نیست")
            else _repeatPasswordValid.emit(null)
        }
    }


    fun setUsernameValid(value : String?) {
        _usernameValid.value = value
    }

    fun nullPasswordValid() {
        _passwordValid.value = null
    }

    fun nullRepeatPasswordValid() {
        _repeatPasswordValid.value = null
    }
}