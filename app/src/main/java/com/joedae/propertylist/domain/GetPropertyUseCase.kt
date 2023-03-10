package com.joedae.propertylist.domain

import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyRepository

class GetPropertyUseCase {
    private val repo: PropertyRepository = PropertyRepository()

    fun getProperties(onDataLoad: OnDataLoad) {
        return repo.getListings(onDataLoad)
    }

    fun getPropertyById(id: String, onDataLoad: OnDataLoad) {
        repo.getListingById(id, onDataLoad)
    }
}
