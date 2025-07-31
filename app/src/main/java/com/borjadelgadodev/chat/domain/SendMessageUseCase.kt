package com.borjadelgadodev.chat.domain

import com.borjadelgadodev.chat.data.network.FirebaseChatService
import com.borjadelgadodev.chat.data.network.dto.MessageDto
import com.borjadelgadodev.chat.data.network.dto.UserDto
import jakarta.inject.Inject
import java.util.Calendar

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(message: String, userName: String) {

        val calendar = Calendar.getInstance()

        val message = message
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto (userName = userName, admin = false)

        val messageDto = MessageDto(
            message = message,
            hour =  "$hour:$minute",
            date = "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMessageToFirebase(messageDto)
    }
}