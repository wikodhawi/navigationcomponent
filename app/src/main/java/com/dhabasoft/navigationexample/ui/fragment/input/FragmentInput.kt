package com.dhabasoft.navigationexample.ui.fragment.input

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navOptions
import com.dhabasoft.navigationexample.MainActivity
import com.dhabasoft.navigationexample.MainViewModel
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentInputBinding
import com.dhabasoft.navigationexample.databinding.FragmentOneBinding
import com.dhabasoft.navigationexample.ui.fragment.one.OneViewModel
import com.dhabasoft.navigationexample.ui.fragment.result.FragmentResult
import com.dhabasoft.navigationexample.utils.ViewModelFactory
import com.javaindoku.www.yotanikerja.utilities.findNavController

/**
 * created by Dhaba
 */
class FragmentInput : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            if (sharedViewModel.position == MainActivity.POSITION_NOTIFICATIONS) {
                val bundle = Bundle()
                bundle.putString(FragmentResult.KEY_INPUT_TEXT, binding.edtInput.text.toString())

                val animView = navOptions {
                    anim {
                        enter = R.anim.slide_in_right
                        exit = R.anim.slide_out_left
                        popEnter = R.anim.slide_in_left
                        popExit = R.anim.slide_out_right
                    }
                }
                findNavController().navigate(R.id.actionNotifications_fragmentInput_to_fragmentResult, bundle, animView)
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