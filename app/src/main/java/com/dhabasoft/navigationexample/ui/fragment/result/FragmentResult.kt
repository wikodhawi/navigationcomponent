package com.dhabasoft.navigationexample.ui.fragment.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.FragmentResultBinding
import com.dhabasoft.navigationexample.utils.findNavController

/**
 * created by Dhaba
 */
class FragmentResult : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
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

        val safeArgs: FragmentResultArgs by navArgs()
        val arg1 = safeArgs.arg1
        val arg2 = safeArgs.arg2
        val argInput = arguments?.getString("myArg")

        val inputText = "${arguments?.getString(KEY_INPUT_TEXT)}\n\nargInput: $argInput\n\nargs1: $arg1\nargs2: $arg2"
        binding.textView.text = inputText
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.actionNotifications_fragmentResult_to_fragmentDeeplink, null, null)
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