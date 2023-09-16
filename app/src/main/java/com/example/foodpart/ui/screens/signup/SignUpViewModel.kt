package com.example.foodpart.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.EditUserPassword
import com.example.foodpart.network.user.RegisterUser
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.network.user.UserData
import com.example.foodpart.network.user.UserResponse
import com.example.foodpart.ui.components.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userApi: UserApi
) : ViewModel() {
    private val _username = MutableStateFlow<String>("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    private val _userRegisterResult = MutableStateFlow<Result>(Result.Idle)
    val userRegisterResult = _userRegisterResult.asStateFlow()

    private val _userResponse = MutableStateFlow<UserData?>(null)
    val userResponse = _userResponse.asStateFlow()

    private val _usernameValid = MutableStateFlow<String?>(null)
    val usernameValid = _usernameValid.asStateFlow()

    private val _passwordValid = MutableStateFlow<String?>(null)
    val passwordValid = _passwordValid.asStateFlow()

    private val _repeatPasswordValid = MutableStateFlow<String?>(null)
    val repeatPasswordValid = _repeatPasswordValid.asStateFlow()

    fun registerUser() {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                _userRegisterResult.emit(Result.Loading)
                val response = userApi.RegisterUser(RegisterUser(username.value,password.value))
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _userResponse.emit(response.body()?.data)
                        _userRegisterResult.emit(Result.Success)
                    } else {
                        _userRegisterResult.emit(Result.Error("body was empty"))
                    }
                } else {
                    _usernameValid.emit("این نام کاربری قبلا انتخاب شده")
                    _userRegisterResult.emit(Result.Error("Validation Error"))
                }
            } catch (t: Throwable) {
                _userRegisterResult.emit(Result.Error("${t.message}"))

            }
        }
    }

    fun setUsername(username: String ){
        viewModelScope.launch(Dispatchers.IO) {
            _username.emit(username)
        }
    }

    fun setPassword (password: String){
        viewModelScope.launch {
            _password.emit(password)
        }
    }


    fun checkUsernameValidation(
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            if (username.value.isEmpty()) _usernameValid.emit("نام کاربری را وارد کنید")
            else if (username.value.length < 4) {
                _usernameValid.emit("نام کاربری خیلی کوچیکه")
            } else
            {_usernameValid.emit(null)}
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
            } else {_passwordValid.emit("رمز عبور باید حداقل ۸ کاراکتر و شامل حروف کوچک و بزرگ باشد")}


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


    fun nullUsernameValid() {
        _usernameValid.value = null
    }

    fun nullPasswordValid() {
        _passwordValid.value = null
    }

    fun nullRepeatPasswordValid() {
        _repeatPasswordValid.value = null
    }
}