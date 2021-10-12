package com.dhabasoft.navigationexample.ui.fragment.three

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by Dhaba
 */
class ThreeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "3"
    }
    val text: LiveData<String> = _text
}