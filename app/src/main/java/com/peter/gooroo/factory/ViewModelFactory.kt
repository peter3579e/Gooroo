package com.peter.gooroo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.gooroo.data.source.GoorooRepository
import com.peter.gooroo.mainpage.MainPageViewModel
import com.peter.gooroo.process.ProcessViewModel

/**
 *
 * Factory for all ViewModels.
 */

class ViewModelFactory (
): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainPageViewModel::class.java)) {
            return MainPageViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}