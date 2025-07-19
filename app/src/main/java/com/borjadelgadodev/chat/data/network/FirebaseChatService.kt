package com.borjadelgadodev.chat.data.network

import com.borjadelgadodev.chat.data.network.dto.MessageDto
import com.borjadelgadodev.chat.data.network.response.MessageResponse
import com.borjadelgadodev.chat.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessageToFirebase(messageDto: MessageDto) {
        val newMessege = reference.child(PATH).push()
        newMessege.setValue(messageDto)
    }

    fun getMessagesFromFirebase(): Flow<List<MessageModel>> {
        return reference.child(PATH).snapshots.map { dataSnapshot ->
           dataSnapshot.children.mapNotNull {
               it.getValue(MessageResponse::class.java)?.toDomain()
           }
        }
    }
}