package com.example.foodpart.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.database.savedfood.SavedFoodDao
import com.example.foodpart.database.savedfood.SavedFoodEntity
import com.example.foodpart.database.user.UserDao
import com.example.foodpart.database.user.UserEntity
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.EditAllUser
import com.example.foodpart.network.user.EditUserPassword
import com.example.foodpart.network.user.EditUserUsername
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.core.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApi: UserApi,
    private val savedFoodDao: SavedFoodDao,
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user = _user.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _oldPassword = MutableStateFlow("")
    val oldPassword = _oldPassword.asStateFlow()

    private val _newPassword = MutableStateFlow("")
    val newPassword = _newPassword.asStateFlow()

    private val _usernameValid = MutableStateFlow<String?>(null)
    val usernameValid = _usernameValid.asStateFlow()

    private val _passwordValid = MutableStateFlow<String?>(null)
    val passwordValid = _passwordValid.asStateFlow()

    private val _editUserResult = MutableStateFlow<Result>(Result.Idle)
    val editUserResult = _editUserResult.asStateFlow()

    private val _savedFoods = MutableStateFlow<List<SavedFoodEntity>>(emptyList())
    val savedFoods = _savedFoods.asStateFlow()


    init {
        observeUser()
        observeSavedFoods()
    }

    private fun observeUser(){
        viewModelScope.launch {
            userDao.observeUser().collect(_user)
        }
    }

    fun isUserLoggedIn():Boolean{
        return user.value != null
    }


    fun editUsername() {

        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    userApi.editUserUsername(
                        body = EditUserUsername(
                            username.value
                        ),
                        token = "Bearer ${user.value?.token}"
                    )
                },

                onDataReady = {
                    viewModelScope.launch {
                        userDao.updateUser(UserEntity(
                            token = user.value?.token?:"",
                            id = user.value?.id,
                            username = username.value,
                            avatar = user.value?.avatar

                        ))
                    }
                }
            ).collect(_editUserResult)


        }
    }

    private fun observeSavedFoods(){
        viewModelScope.launch (Dispatchers.IO) {
            savedFoodDao.observeFoods().collect(_savedFoods)
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
                            body = EditUserPassword(
                                oldPassword = oldPassword.value,
                                newpassword = newPassword.value
                            ),
                            token = "Bearer ${user.value?.token}"
                        )

                    },
                    onDataReady = {
                        viewModelScope.launch {
                            userDao.updateUser(UserEntity(
                                token = it.additionalInfo.token,
                                id = user.value?.id,
                                username = username.value,
                                avatar = user.value?.avatar

                            ))
                        }
                    }
                ).collect(_editUserResult)
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
                            token = "Bearer ${user.value?.token}",
                            body = EditAllUser(
                                username.value,
                                oldPassword.value,
                                newPassword.value
                            )
                        )
                    },
                    onDataReady = {
                        viewModelScope.launch {
                            userDao.updateUser(
                                UserEntity(
                                    it.additionalInfo.token,
                                    username.value,
                                    user.value?.id,
                                    user.value?.avatar
                                )
                            )
                        }
                    }
                ).collect(_editUserResult)

            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                userApi.logout(token = "Bearer ${user.value?.token}")
                userDao.deleteUser()

            } catch (t: Throwable) {
                Log.d("error", "logout error: ${t.message}")
            }
        }


    }

    fun nullUsernameValid() {
        _usernameValid.value = null
    }

    fun nullPasswordValid() {
        _passwordValid.value = null
    }


    fun setUsername(value: String) {
        viewModelScope.launch {
            _username.emit(value)
        }
    }

    fun setOldPassword(value: String) {
        viewModelScope.launch {
            _oldPassword.emit(value)
        }
    }

    fun setNewPassword(value: String) {
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
            } else {
                _usernameValid.emit(null)
            }
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
            } else {
                _passwordValid.emit("رمز عبور باید حداقل ۸ کاراکتر و شامل حروف کوچک و بزرگ باشد")
            }


        }
    }



}