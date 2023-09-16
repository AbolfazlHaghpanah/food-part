package com.example.foodpart.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.core.UserInfo
import com.example.foodpart.core.UserInfo.token
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

    private val _userResponse = MutableStateFlow<UserData?>(null)
    val userResponse = _userResponse.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    fun registerUserInApp(){
        UserInfo.token = _token.value
        UserInfo.avatar = _userResponse.value?.avatar
        UserInfo.id = _userResponse.value?.id
        UserInfo.username = _userResponse.value?.username?:""

    }
    fun loginUser(){
        viewModelScope.launch (Dispatchers.IO){



            try {
                _userLoginResult.emit(Result.Loading)
                val response = userApi.loginUser(RegisterUser(username.value,password.value))
                if (response.isSuccessful){
                    if (response.body() != null){
                        Log.d("TAG", "loginUser: test 1")
                        _token.emit(response.body()?.data?.token)
                        _userResponse.emit(response.body()?.data?.user)
                        registerUserInApp()
                        _userLoginResult.emit(Result.Success)
                    }else{
                        Log.d("TAG", "loginUser: test 2")
                        _userLoginResult.emit(Result.Error(response.message()))
                    }
                }
            }catch (t: Throwable){
                _userLoginResult.emit(Result.Error("${t.message}"))
                Log.d("TAG", "loginUser: test 3")

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