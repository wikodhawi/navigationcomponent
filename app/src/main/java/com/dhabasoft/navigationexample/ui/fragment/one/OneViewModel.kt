package com.dhabasoft.navigationexample.ui.fragment.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by Dhaba
 */
class OneViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "1"
    }
    val text: LiveData<String> = _text
}