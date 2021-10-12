package com.dhabasoft.navigationexample.ui.fragment.two

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by Dhaba
 */
class TwoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "2"
    }
    val text: LiveData<String> = _text
}