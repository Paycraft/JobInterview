package com.joedae.propertylist.data.db

import com.joedae.propertylist.data.Property

interface CallBackActions {

    fun onSetFavorite(id: String) {}

    fun openPDP(id: String) {}

}