package com.joedae.propertylist.data;

interface IPropertyRepo {
    fun getListings(onDataLoad: OnDataLoad)
    fun getListingById(id: String, onDataLoad: OnDataLoad)
}
