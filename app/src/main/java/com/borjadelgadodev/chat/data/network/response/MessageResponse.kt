package com.borjadelgadodev.chat.data.network.response

import com.borjadelgadodev.chat.domain.model.MessageModel
import com.borjadelgadodev.chat.domain.model.UserModel

data class MessageResponse(
    val message: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {

    fun toDomain(): MessageModel {
        return MessageModel(
            message = message.orEmpty(),
            hour = hour.orEmpty(),
            date = date.orEmpty(),
            user = UserModel(
                userName = user?.userName ?: "Unknown User",
                admin = user?.admin ?: false
            )
        )
    }
}

data class UserResponse(
    val userName: String? = null,
    val admin: Boolean? = null,
)
