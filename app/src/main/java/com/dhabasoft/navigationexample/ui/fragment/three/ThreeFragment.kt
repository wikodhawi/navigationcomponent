package com.dhabasoft.navigationexample.ui.fragment.three

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhabasoft.navigationexample.ui.main.MainActivity
import com.dhabasoft.navigationexample.ui.main.MainViewModel
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentThreeBinding
import com.dhabasoft.navigationexample.utils.ViewModelFactory
import com.dhabasoft.navigationexample.utils.findNavController

/**
 * created by Dhaba
 */
class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ThreeViewModel
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(ThreeViewModel::class.java)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner, {
            binding.textView.text = it
        })
        binding.textView.setOnClickListener {
            if (sharedViewModel.position == MainActivity.POSITION_CHAT) {
                findNavController().navigate(R.id.actionChat_fragmentThree_to_fragmentOne, null, null)
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