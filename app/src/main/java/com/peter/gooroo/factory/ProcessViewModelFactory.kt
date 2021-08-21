package com.peter.gooroo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.gooroo.data.source.GoorooRepository
import com.peter.gooroo.process.ProcessViewModel

/**
 *
 * Factory for Process ViewModels.
 */

class ProcessViewModelFactory (
    private val goorooRepository: GoorooRepository,
    private val number: Int
        ): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ProcessViewModel::class.java)) {
            return ProcessViewModel(goorooRepository, number) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}