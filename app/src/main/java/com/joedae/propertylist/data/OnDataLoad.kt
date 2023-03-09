package com.joedae.propertylist.data

interface OnDataLoad {

    fun onDataLoad(propertyResponse: PropertyResponse?) {}
    fun onDetailLoad(propertyDetailResponse: PropertyDetailResponse?) {}
}