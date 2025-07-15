package com.borjadelgadodev.chat.ui.chat

import androidx.lifecycle.ViewModel
import com.borjadelgadodev.chat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val sendMessageUseCase: SendMessageUseCase
): ViewModel() {

    fun sendMessage() {
        val message = "Holiwi"
        sendMessageUseCase(message)
    }
}