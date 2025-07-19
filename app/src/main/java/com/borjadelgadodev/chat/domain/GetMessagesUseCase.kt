package com.borjadelgadodev.chat.domain

import com.borjadelgadodev.chat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val firebaseChatService: FirebaseChatService,
) {
    operator fun invoke() = firebaseChatService.getMessagesFromFirebase()
}
