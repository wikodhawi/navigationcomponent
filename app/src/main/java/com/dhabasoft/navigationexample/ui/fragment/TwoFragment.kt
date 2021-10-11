package com.dhabasoft.navigationexample.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dhabasoft.navigationexample.databinding.FragmentTwoBinding
import com.javaindoku.www.yotanikerja.utilities.findNavController

/**
 * created by Dhaba
 */
class TwoFragment : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {

        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        if(findNavController().backStack.size != 0) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            binding.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}