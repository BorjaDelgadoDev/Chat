package com.borjadelgadodev.chat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.borjadelgadodev.chat.R
import com.borjadelgadodev.chat.databinding.FragmentChatBinding
import com.borjadelgadodev.chat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var binding: FragmentChatBinding
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.imageViewBack.setOnClickListener {
            navigateToMain()
        }

        initUI()

        binding.buttonSendMessage.setOnClickListener {
            viewModel.sendMessage()
        }

        return binding.root
    }

    private fun initUI() {
        initMessages()
        observeViewModel()
    }

    private fun initMessages() {
        chatAdapter = ChatAdapter(
            messageList = mutableListOf(),
            userName = "borja"
        )
        binding.recyclerViewMessages.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.messageListResponse.collect { messages ->
                chatAdapter.updateList(messages.toMutableList())
                binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
            }
        }
    }

    private fun navigateToMain() {
        findNavController().popBackStack()
    }
}
