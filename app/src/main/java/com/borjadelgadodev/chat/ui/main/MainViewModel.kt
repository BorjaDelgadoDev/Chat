package com.borjadelgadodev.chat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.chat.domain.GetUserNameUseCase
import com.borjadelgadodev.chat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val viewState: StateFlow<MainViewState> = _viewState

    init {
        verifyUserLogged()
    }

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val userName = async { getUserNameUseCase() }.await()
            if (userName.isNotEmpty()) {
                _viewState.value = MainViewState.REGISTERED
            } else {
                _viewState.value = MainViewState.UNREGISTERED
            }
        }
    }

    fun saveNickName(nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserNameUseCase(nickName)
        }
    }
}

sealed class MainViewState {
    object LOADING : MainViewState()
    object UNREGISTERED : MainViewState()
    object REGISTERED : MainViewState()
}
