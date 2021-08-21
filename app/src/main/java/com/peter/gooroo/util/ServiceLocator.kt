package com.peter.gooroo.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.peter.gooroo.data.source.DefaultGoorooRepository
import com.peter.gooroo.data.source.GoorooDataSource
import com.peter.gooroo.data.source.GoorooRepository
import com.peter.gooroo.data.source.remote.GoorooRemoteDataSource

/**
 * A Service Locator for the [GoorooRepository].
 */

object ServiceLocator {

    @Volatile
    var stylishRepository: GoorooRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(): GoorooRepository {
        synchronized(this) {
            return stylishRepository
                    ?: stylishRepository
                    ?: createGoorooRepository()
        }
    }

    private fun createGoorooRepository(): GoorooRepository {
        return DefaultGoorooRepository(GoorooRemoteDataSource)
    }

}