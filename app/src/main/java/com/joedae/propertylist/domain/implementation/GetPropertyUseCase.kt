package com.joedae.propertylist.domain.implementation

import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyRepository
import com.joedae.propertylist.domain.IGetPropertyUseCase
import javax.inject.Inject


class GetPropertyUseCase @Inject constructor(): IGetPropertyUseCase  {
    private val repo: PropertyRepository = PropertyRepository()

    override fun getProperties(onDataLoad: OnDataLoad) {
        return repo.getListings(onDataLoad)
    }

    override fun getPropertyById(id: String, onDataLoad: OnDataLoad) {
        repo.getListingById(id, onDataLoad)
    }
}
