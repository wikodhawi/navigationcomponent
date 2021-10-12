package com.dhabasoft.navigationexample.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var navControllerDashboard: NavController

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hostHome: NavHostFragment = childFragmentManager.findFragmentById(R.id.navHostDashboard) as NavHostFragment? ?: return
        navControllerDashboard = hostHome.navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}