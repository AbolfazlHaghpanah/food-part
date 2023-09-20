package com.example.foodpart.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.database.user.UserDao
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.RegisterUser
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.core.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
):ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userLoginResult = MutableStateFlow<Result>(Result.Idle)
    val userLoginResult = _userLoginResult.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    private val _isUserinfoTrue = MutableStateFlow(true)
    val isUserInfoTrue = _isUserinfoTrue.asStateFlow()

    private val _isUserNameValid = MutableStateFlow(true)
    val isUsernameValid = _isUserNameValid.asStateFlow()

    private val _isPasswordValid = MutableStateFlow(true)
    val isPasswordValid = _isPasswordValid.asStateFlow()




    fun loginUser(){
        viewModelScope.launch (Dispatchers.IO){
            safeApi(
                call = {
                    userApi.loginUser(RegisterUser(username.value,password.value))
                },
                onDataReady = {
                    viewModelScope.launch {
                        userDao.addUser(it.toUserEntity())
                    }
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

    fun setIsPasswordValid(value : Boolean){
        viewModelScope.launch {
            _isPasswordValid.emit(value)
        }
    }

    fun setIsUsernameValid(value : Boolean){
        viewModelScope.launch {
            _isUserNameValid.emit(value)
        }
    }
}