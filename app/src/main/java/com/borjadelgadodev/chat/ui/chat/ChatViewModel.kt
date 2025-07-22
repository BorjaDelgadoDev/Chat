package com.borjadelgadodev.chat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.chat.data.network.response.MessageResponse
import com.borjadelgadodev.chat.domain.GetMessagesUseCase
import com.borjadelgadodev.chat.domain.SendMessageUseCase
import com.borjadelgadodev.chat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    val sendMessageUseCase: SendMessageUseCase,
    val getMessagesUseCase: GetMessagesUseCase,
) : ViewModel() {

    init {
        getMessages()
    }

    private var _messageListResponse = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageListResponse: StateFlow<List<MessageModel>> = _messageListResponse

    private fun getMessages() {
        viewModelScope.launch {
            val result = getMessagesUseCase()
            result.collect { messages ->
                Log.d("ChatViewModel", "Messages received: $messages")
                _messageListResponse.value = messages
            }
        }
    }

    fun sendMessage() {
        val message = "Holiwi"
        sendMessageUseCase(message)
    }
}
