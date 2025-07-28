package com.borjadelgadodev.chat.domain

interface DatabaseService {
    suspend fun saveUserName(nickName: String)
}