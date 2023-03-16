package com.joedae.propertylist.domain

import com.joedae.propertylist.data.OnDataLoad

interface IGetPropertyUseCase {
    fun getProperties(onDataLoad: OnDataLoad) {}

    fun getPropertyById(id: String, onDataLoad: OnDataLoad) {}
}