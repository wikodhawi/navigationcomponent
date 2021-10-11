package com.dhabasoft.navigationexample.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhabasoft.navigationexample.MainActivity
import com.dhabasoft.navigationexample.MainViewModel
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentOneBinding
import com.javaindoku.www.yotanikerja.utilities.findNavController

/**
 * created by Dhaba
 */
class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            if (sharedViewModel.position == MainActivity.POSITION_DASHBOARD) {
                findNavController().navigate(R.id.actionDashboard_fragmentOne_to_fragmentTwo, null, null)
            }
            else if(sharedViewModel.position == MainActivity.POSITION_HOME) {
                findNavController().navigate(R.id.actionHome_fragmentOne_to_fragmentTwo, null, null)
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        if(findNavController().backStack.size != 0) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
            binding.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}