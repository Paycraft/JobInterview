package com.joedae.propertylist

import android.app.Application
import com.joedae.propertylist.di.PropertyComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PropertyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PropertyComponent.setContext(this)
    }
}
