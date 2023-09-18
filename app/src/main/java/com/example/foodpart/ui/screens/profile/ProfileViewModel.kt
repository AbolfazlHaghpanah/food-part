package com.example.foodpart.ui.screens.profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.core.UserInfo
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.EditAllUser
import com.example.foodpart.network.user.EditUserPassword
import com.example.foodpart.network.user.EditUserUsername
import com.example.foodpart.network.user.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApi: UserApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _username = MutableStateFlow<String>("")
    val username = _username.asStateFlow()

    private val _oldPassword = MutableStateFlow<String>("")
    val oldPassword = _oldPassword.asStateFlow()

    private val _newPassword = MutableStateFlow<String>("")
    val newPassword = _newPassword.asStateFlow()

    private val _usernameValid = MutableStateFlow<String?>(null)
    val usernameValid = _usernameValid.asStateFlow()

    private val _passwordValid = MutableStateFlow<String?>(null)
    val passwordValid = _passwordValid.asStateFlow()



    fun editUsername() {

        viewModelScope.launch(Dispatchers.IO) {

            if (username.value.isNotEmpty()) {

                safeApi(
                    call = {
                        userApi.editUserUsername(
                            body = EditUserUsername(
                                username.value
                            )
                        )
                    },

                    onDataReady = {
                        UserInfo.username = username.value
                    }
                ).collect()

            }

        }
    }

    fun editPassword() {
        viewModelScope.launch(Dispatchers.IO) {
            if (
                newPassword.value.isNotEmpty()
                && oldPassword.value.isNotEmpty()
            ) {

                safeApi(
                    call = {
                        userApi.editUserPassword(
                            EditUserPassword(
                                oldPassword = oldPassword.value,
                                newpassword = newPassword.value
                            )
                        )
                    },
                    onDataReady = {
                        UserInfo.token = it.additionalInfo.token
                        Log.d("editUser", "editPassword: passwordChanged")
                    }
                ).collect()
            }
        }
    }

    fun editAll() {
        viewModelScope.launch(Dispatchers.IO) {

            if (
                username.value.isNotEmpty()
                && newPassword.value.isNotEmpty()
                && oldPassword.value.isNotEmpty()
            ) {
                safeApi(
                    call = {
                        userApi.editUserAll(
                            EditAllUser(
                                username.value,
                                oldPassword.value,
                                newPassword.value
                            )
                        )
                    },
                    onDataReady = {
                        UserInfo.token = it.additionalInfo.token
                        UserInfo.username = username.value
                    }
                ).collect()

            }
        }
    }


    fun setUsername(value : String){
        viewModelScope.launch {
            _username.emit(value)
        }
    }

    fun setOldPassword(value : String){
        viewModelScope.launch {
            _oldPassword.emit(value)
        }
    }

    fun setNewPassword(value : String){
        viewModelScope.launch {
            _newPassword.emit(value)
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
            if (newPassword.value.isEmpty()) _passwordValid.emit("رمز عبور را وارد کنید")
            else if (newPassword.value.contains(Regex("[a-z]"))
                && newPassword.value.contains(Regex("[A-Z]"))
                && newPassword.value.length >= 8
            ) {
                _passwordValid.emit(null)
            } else {_passwordValid.emit("رمز عبور باید حداقل ۸ کاراکتر و شامل حروف کوچک و بزرگ باشد")}


        }
    }

}