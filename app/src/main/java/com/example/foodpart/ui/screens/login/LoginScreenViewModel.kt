package com.example.foodpart.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.foodpart.network.user.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userApi: UserApi
):ViewModel() {


}