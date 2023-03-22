package com.joedae.propertylist.domain.implementation

import com.joedae.propertylist.data.IPropertyRepo
import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.domain.IGetPropertyUseCase
import javax.inject.Inject


class GetPropertyUseCase @Inject constructor(private val propertyRepo: IPropertyRepo) :
    IGetPropertyUseCase {

    override fun getProperties(onDataLoad: OnDataLoad) {
        return propertyRepo.getListings(onDataLoad)
    }

    override fun getPropertyById(id: String, onDataLoad: OnDataLoad) {
        propertyRepo.getListingById(id, onDataLoad)
    }
}
