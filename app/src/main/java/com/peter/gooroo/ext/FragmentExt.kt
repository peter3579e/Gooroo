package com.peter.gooroo.ext

import androidx.fragment.app.Fragment
import com.peter.gooroo.GoorooApplication
import com.peter.gooroo.factory.ProcessViewModelFactory
import com.peter.gooroo.factory.ViewModelFactory


/**
 *
 * Extension functions for Fragment.
 */

fun Fragment.getVmFactory(number: Int): ProcessViewModelFactory {
    val repository = (requireContext().applicationContext as GoorooApplication).goorooRepository
    return ProcessViewModelFactory(repository, number)
}

fun Fragment.getVmFactory(): ViewModelFactory {
    return ViewModelFactory()
}