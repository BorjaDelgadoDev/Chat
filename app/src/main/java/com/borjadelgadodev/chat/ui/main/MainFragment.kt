package com.borjadelgadodev.chat.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.borjadelgadodev.chat.databinding.FragmentMainBinding
import com.borjadelgadodev.chat.R
import com.borjadelgadodev.chat.ui.chat.ChatViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    var userName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.buttonStartChat.setOnClickListener {
            userName = binding.inputTextUserName.text.toString()
            userName?.let { userName ->
                if (userName.isNotEmpty()) {
                    viewModel.saveNickName(userName)
                    navigateToChat()
                } else {
                    binding.inputTextUserName.error = getString(R.string.error_empty_username)
                }
            }
        }
        return binding.root
    }

    private fun navigateToChat() {
        findNavController().navigate(R.id.action_fragment_main_to_fragment_chat)
    }
}
