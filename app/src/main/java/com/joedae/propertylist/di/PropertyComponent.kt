package com.joedae.propertylist.di

import android.content.Context


object PropertyComponent {
    private lateinit var context: Context

    public fun setContext(context: Context) {
        this.context = context
    }

    public fun getContext(): Context {
        return this.context
    }
}