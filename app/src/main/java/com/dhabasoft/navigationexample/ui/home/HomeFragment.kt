package com.dhabasoft.navigationexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var navControllerHome: NavController
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hostHome: NavHostFragment = childFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment? ?: return
        navControllerHome = hostHome.navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}