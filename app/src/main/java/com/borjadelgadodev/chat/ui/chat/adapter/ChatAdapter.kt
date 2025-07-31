package com.borjadelgadodev.chat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.borjadelgadodev.chat.databinding.ItemChatMeBinding
import com.borjadelgadodev.chat.databinding.ItemChatOtherBinding
import com.borjadelgadodev.chat.domain.model.MessageModel

class ChatAdapter(
    private var messageList: MutableList<MessageModel>,
    private var userName: String = ""
) : RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    fun updateList(messages: MutableList<MessageModel>, name: String) {
        userName = name
        messageList.clear()
        messageList.addAll(messages)
        notifyItemInserted(messageList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = when (viewType) {
            SENT_MESSAGE -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            RECEIVED_MESSAGE -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position], getItemViewType(position))
    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].user.userName == userName) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }
    }
}
