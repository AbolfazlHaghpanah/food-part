package com.example.foodpart.core

import com.example.foodpart.network.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

object UserInfo{
    var token= MutableStateFlow<String?>(null)
    var avatar = MutableStateFlow<String?>(null)
    var id = MutableStateFlow<String?>(null)
    var username = MutableStateFlow<String?>(null)

}