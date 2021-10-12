package com.dhabasoft.navigationexample.ui.fragment.result

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
import com.dhabasoft.navigationexample.databinding.FragmentResultBinding
import com.dhabasoft.navigationexample.utils.ViewModelFactory
import com.javaindoku.www.yotanikerja.utilities.findNavController

/**
 * created by Dhaba
 */
class FragmentResult : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    companion object {
        const val KEY_INPUT_TEXT = "keyInputText"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputText = arguments?.getString(KEY_INPUT_TEXT)
        binding.textView.text = inputText
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