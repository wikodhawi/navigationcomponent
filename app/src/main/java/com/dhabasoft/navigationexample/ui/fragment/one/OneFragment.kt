package com.dhabasoft.navigationexample.ui.fragment.one

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
import com.dhabasoft.navigationexample.utils.ViewModelFactory
import com.dhabasoft.navigationexample.utils.findNavController

/**
 * created by Dhaba
 */
class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    private lateinit var viewModel: OneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(OneViewModel::class.java)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner, {
            binding.textView.text = it
        })
        binding.textView.setOnClickListener {
            if (sharedViewModel.position == MainActivity.POSITION_DASHBOARD) {
                findNavController().navigate(R.id.actionDashboard_fragmentOne_to_fragmentTwo, null, null)
            }
            else if(sharedViewModel.position == MainActivity.POSITION_HOME) {
                findNavController().navigate(R.id.actionHome_fragmentOne_to_fragmentTwo, null, null)
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        if(findNavController().graph.startDestination != findNavController().currentDestination?.id) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            binding.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        else {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }
}