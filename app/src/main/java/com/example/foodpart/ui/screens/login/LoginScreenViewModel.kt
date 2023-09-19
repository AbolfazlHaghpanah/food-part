package com.example.foodpart.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.core.UserInfo
import com.example.foodpart.network.user.LoginUserResponse
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

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    private val _isUserinfoTrue = MutableStateFlow<Boolean>(true)
    val isUserInfoTrue = _isUserinfoTrue.asStateFlow()

    fun registerUserInApp(){

        viewModelScope.launch {
            UserInfo.token.emit(token.value)
            UserInfo.avatar.emit(userResponse.value?.user?.avatar)
            UserInfo.id.emit(userResponse.value?.user?.id)
            UserInfo.username.emit(userResponse.value?.user?.username)
        }


    }
    fun loginUser(){
        viewModelScope.launch (Dispatchers.IO){

            try {
                _userLoginResult.emit(Result.Loading)
                val response = userApi.loginUser(RegisterUser(username.value,password.value))
                if (response.isSuccessful){
                    if (response.body() != null){
                        _token.emit(response.body()?.data?.token)
                        _userResponse.emit(response.body()?.data)
                        registerUserInApp()
                        _userLoginResult.emit(Result.Success)
                    }else{
                        _userLoginResult.emit(Result.Error(response.message()))
                    }
                }else{
                    _isUserinfoTrue.emit(false)
                    _userLoginResult.emit(Result.Error(response.message()))
                }
            }catch (t: Throwable){
                _userLoginResult.emit(Result.Error("no_status"))
                Log.e("error", "loginUser: ${t.message}" )

            }



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