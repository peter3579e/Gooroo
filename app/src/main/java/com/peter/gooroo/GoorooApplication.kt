package com.peter.gooroo

import android.app.Application
import android.content.Context
import com.peter.gooroo.data.source.GoorooRepository
import com.peter.gooroo.util.ServiceLocator
import kotlin.properties.Delegates

/**
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify the sample. Consider a Dependency Injection framework.
 */

class GoorooApplication : Application() {

    // Depends on the flavor,
    val goorooRepository: GoorooRepository
        get() = ServiceLocator.provideTasksRepository()


    companion object {
        var instance: GoorooApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}