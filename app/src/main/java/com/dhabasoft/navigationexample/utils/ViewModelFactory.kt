package com.dhabasoft.navigationexample.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhabasoft.navigationexample.ui.main.MainViewModel
import com.dhabasoft.navigationexample.ui.fragment.one.OneViewModel
import com.dhabasoft.navigationexample.ui.fragment.three.ThreeViewModel
import com.dhabasoft.navigationexample.ui.fragment.two.TwoViewModel

/**
 * created by Dhaba
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            modelClass.isAssignableFrom(OneViewModel::class.java) -> {
                OneViewModel() as T
            }
            modelClass.isAssignableFrom(TwoViewModel::class.java) -> {
                TwoViewModel() as T
            }
            modelClass.isAssignableFrom(ThreeViewModel::class.java) -> {
                ThreeViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
