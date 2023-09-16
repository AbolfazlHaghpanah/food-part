package com.example.foodpart.ui.screens.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.core.UserInfo
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.user.UserApi
import com.example.foodpart.ui.components.Result
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
):ViewModel() {


}