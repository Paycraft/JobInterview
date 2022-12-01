package com.joedae.propertylist.api

import com.joedae.propertylist.data.PropertyResponse
import retrofit2.Call
import retrofit2.http.GET

interface PropertyService {
    @GET("/properties")
    fun getProperty(): Call<PropertyResponse>
}