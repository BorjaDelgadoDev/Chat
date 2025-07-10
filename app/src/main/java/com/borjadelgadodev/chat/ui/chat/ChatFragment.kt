package com.borjadelgadodev.chat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.borjadelgadodev.chat.R
import com.borjadelgadodev.chat.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.imageViewBack.setOnClickListener {
            navigateToMain()
        }
        return binding.root
    }

    private fun navigateToMain() {
        findNavController().popBackStack()
    }
}
