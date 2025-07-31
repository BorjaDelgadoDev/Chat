package com.borjadelgadodev.chat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.chat.domain.GetMessagesUseCase
import com.borjadelgadodev.chat.domain.GetUserNameUseCase
import com.borjadelgadodev.chat.domain.LogoutUseCase
import com.borjadelgadodev.chat.domain.SendMessageUseCase
import com.borjadelgadodev.chat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChatViewModel @Inject constructor(
    val sendMessageUseCase: SendMessageUseCase,
    val getMessagesUseCase: GetMessagesUseCase,
    val getUserNameUseCase: GetUserNameUseCase,
    val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _messageListResponse = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageListResponse: StateFlow<List<MessageModel>> = _messageListResponse
    private val _userName = MutableStateFlow("")
    val userNameFlow: StateFlow<String> = _userName

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            val name = getUserNameUseCase()
            withContext(Dispatchers.Main) {
                _userName.value = name
            }
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            val result = getMessagesUseCase()
            result.collect { messages ->
                _messageListResponse.value = messages
            }
        }
    }

    fun sendMessage(message: String, userName: String) {
        sendMessageUseCase(message, userName)
    }

    fun logout(onViewFinish: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            async { logoutUseCase }.await()
            withContext(Dispatchers.Main) {
                onViewFinish()
            }
        }
    }
}
