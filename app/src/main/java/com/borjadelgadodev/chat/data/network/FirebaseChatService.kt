package com.borjadelgadodev.chat.data.network

import com.google.firebase.database.DatabaseReference
import jakarta.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessageToFirebase(message: String) {
        val newMessege = reference.child(PATH).push()
        newMessege.setValue(message)
    }
}