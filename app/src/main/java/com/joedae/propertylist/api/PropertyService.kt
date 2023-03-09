package com.joedae.propertylist.api

import com.joedae.propertylist.data.Property
import com.joedae.propertylist.data.PropertyDetailResponse
import com.joedae.propertylist.data.PropertyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyService {
    @GET("/properties")
    fun getProperty(): Call<PropertyResponse>

    @GET("/properties/{id}")
    fun getPropertyByID(@Path("id") id: String): Call<PropertyDetailResponse>
}