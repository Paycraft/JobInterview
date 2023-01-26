package com.joedae.propertylist

import android.app.Application
import com.joedae.propertylist.di.PropertyComponent

class PropertyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PropertyComponent.setContext(this)
    }
}