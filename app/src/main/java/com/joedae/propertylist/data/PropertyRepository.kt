package com.joedae.propertylist.data

import android.util.Log
import com.joedae.propertylist.api.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PropertyRepository {

    private lateinit var apiResponse: PropertyResponse

    fun getListings(): PropertyResponse {
        val service = Retrofit.Builder()
            .baseUrl("https://private-9f1bb1-homegate3.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PropertyService::class.java)

        service.getProperty().enqueue(object : Callback<PropertyResponse> {
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.i("API Response", "Failed")
            }
            override fun onResponse(call: Call<PropertyResponse>, response: Response<PropertyResponse>) {
                Log.i("API Response", response.body().toString())
                apiResponse = response.body()!!
            }
        })
        return apiResponse
    }
}