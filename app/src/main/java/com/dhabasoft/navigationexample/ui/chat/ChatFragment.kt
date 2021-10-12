package com.dhabasoft.navigationexample.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentChatBinding

/**
 * created by Dhaba
 */
class ChatFragment : Fragment() {
    private lateinit var navControllerChat: NavController
    private var _binding: FragmentChatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hostHome: NavHostFragment = childFragmentManager.findFragmentById(R.id.navHostChat) as NavHostFragment? ?: return
        navControllerChat = hostHome.navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}