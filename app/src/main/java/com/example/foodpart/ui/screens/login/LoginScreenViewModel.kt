package com.example.foodpart.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.database.user.UserDao
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
    private val userApi: UserApi,
    private val userDao: UserDao
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



    fun loginUser(){
        viewModelScope.launch (Dispatchers.IO){

            try {
                _userLoginResult.emit(Result.Loading)
                val response = userApi.loginUser(RegisterUser(username.value,password.value))
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        userDao.addUser(body.toUserEntity())
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