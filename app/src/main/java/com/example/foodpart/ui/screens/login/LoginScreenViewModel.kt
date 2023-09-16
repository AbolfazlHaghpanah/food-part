package com.example.foodpart.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.EditUserPassword
import com.example.foodpart.network.user.EditUserUsername
import com.example.foodpart.network.user.LoginUserResponse
import com.example.foodpart.network.user.RegisterUser
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.network.user.UserData
import com.example.foodpart.ui.components.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userApi: UserApi
):ViewModel() {

    private val _username = MutableStateFlow<String>("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    private val _userLoginResult = MutableStateFlow<Result>(Result.Idle)
    val userLoginResult = _userLoginResult.asStateFlow()

    private val _userResponse = MutableStateFlow<LoginUserResponse?>(null)
    val userResponse = _userResponse.asStateFlow()

    fun loginUser(){
        viewModelScope.launch (Dispatchers.IO){
            safeApi(
                call = {
                       userApi.loginUser(RegisterUser(username.value,password.value))
                },
                onDataReady = {
                    _userResponse.value = it
                }
            ).collect(_userLoginResult)
        }
    }

    fun setUsername(username: String){
        viewModelScope.launch {
            _username.emit(username)
        }
    }

    fun setPassword(password: String){
        viewModelScope.launch{
            _password.emit(password)
        }
    }
}