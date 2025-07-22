package com.borjadelgadodev.chat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.borjadelgadodev.chat.databinding.ItemChatMeBinding
import com.borjadelgadodev.chat.databinding.ItemChatOtherBinding
import com.borjadelgadodev.chat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: MessageModel, itemViewType: Int) {
        when (itemViewType) {
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(model)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceivedMessage(model)
        }

    }

    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.textViewDateMe.text = messageModel.date
        currentBinding.textViewChatMe.text = messageModel.message
        currentBinding.textViewHourMe.text = messageModel.hour
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.textViewOtherDate.text = messageModel.date
        currentBinding.textViewOtherChat.text = messageModel.message
        currentBinding.textViewOtherName.text = messageModel.user.userName
        currentBinding.textViewOtherHour.text = messageModel.hour
    }
}
