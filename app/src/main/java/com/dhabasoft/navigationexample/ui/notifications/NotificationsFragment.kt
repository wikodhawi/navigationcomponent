package com.dhabasoft.navigationexample.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private lateinit var navControllerNotifications: NavController
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hostNotifications: NavHostFragment = childFragmentManager.findFragmentById(R.id.navHostNotifications) as NavHostFragment? ?: return
        navControllerNotifications = hostNotifications.navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}