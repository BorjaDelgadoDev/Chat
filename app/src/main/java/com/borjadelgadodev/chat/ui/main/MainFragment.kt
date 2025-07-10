package com.borjadelgadodev.chat.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.borjadelgadodev.chat.databinding.FragmentMainBinding
import com.borjadelgadodev.chat.R

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.buttonStartChat.setOnClickListener {
            if (binding.inputTextUserName.text.isNullOrEmpty().not()) {
                navigateToChat()
            }
        }
        return binding.root
    }

    private fun navigateToChat() {
        findNavController().navigate(R.id.action_fragment_main_to_fragment_chat)
    }
}
