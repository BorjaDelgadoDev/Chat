package com.borjadelgadodev.chat.domain.model

data class MessageModel (
    val message: String,
    val hour: String,
    val date: String,
    val userModel: UserModel
)

data class UserModel(
    val userName: String,
    val admin: Boolean = false,
)