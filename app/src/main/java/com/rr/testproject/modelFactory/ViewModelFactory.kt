package com.rr.testproject.modelFactory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rr.testproject.mainUI.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(activity) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}