package com.peter.gooroo

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class GoorooApplication : Application() {


    companion object {
        var instance: GoorooApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}