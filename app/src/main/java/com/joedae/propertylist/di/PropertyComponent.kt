package com.joedae.propertylist.di

import android.content.Context

object  PropertyComponent {
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun getContext(): Context {
        return this.context
    }
}