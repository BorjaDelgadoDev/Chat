package com.borjadelgadodev.chat.domain

import com.borjadelgadodev.chat.data.network.FirebaseChatService
import jakarta.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(message: String) {
        firebaseChatService.sendMessageToFirebase(message)
    }
}