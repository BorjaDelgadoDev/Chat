package com.borjadelgadodev.chat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveUserName(nickName: String)
    fun getUserName(): Flow<String>
    suspend fun clear()
}