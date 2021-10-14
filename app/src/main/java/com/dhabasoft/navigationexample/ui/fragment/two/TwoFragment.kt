package com.dhabasoft.navigationexample.ui.fragment.two

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
import com.dhabasoft.navigationexample.databinding.FragmentTwoBinding
import com.dhabasoft.navigationexample.utils.ViewModelFactory
import com.dhabasoft.navigationexample.utils.findNavController

/**
 * created by Dhaba
 */
class TwoFragment : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TwoViewModel
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(TwoViewModel::class.java)
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
                if(findNavController().currentDestination?.id == R.id.fragmentTwoHome)
                    findNavController().navigate(R.id.actionHome_fragmentTwo_to_fragmentThree, null, null)
                else
                    findNavController().navigate(R.id.actionDashboard_fragmentTwo_to_fragmentThree, null, null)
            }
            else if(sharedViewModel.position == MainActivity.POSITION_HOME) {
                findNavController().navigate(R.id.actionHome_fragmentTwo_to_fragmentThree, null, null)
            }
            else if(sharedViewModel.position == MainActivity.POSITION_CHAT) {
                findNavController().navigate(R.id.actionChat_fragmentTwo_to_fragmentThree, null, null)
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