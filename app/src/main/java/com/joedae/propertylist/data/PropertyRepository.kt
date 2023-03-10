package com.joedae.propertylist.data

import android.util.Log
import com.joedae.propertylist.api.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PropertyRepository {
    fun getListings(onDataLoad: OnDataLoad) {
        val service = Retrofit.Builder()
            .baseUrl("https://private-9f1bb1-homegate3.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PropertyService::class.java)

        service.getProperty().enqueue(object : Callback<PropertyResponse> {
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.i("APIResponse", "Load all propertys failed")
            }

            override fun onResponse(
                call: Call<PropertyResponse>,
                response: Response<PropertyResponse>
            ) {
                Log.i("APIResponse", response.body().toString())
                onDataLoad.onDataLoad(response.body())
            }
        })
    }

    fun getListingById(id: String, onDataLoad: OnDataLoad) {
        val service = Retrofit.Builder()
            .baseUrl("https://private-9f1bb1-homegate3.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PropertyService::class.java)

        service.getPropertyByID(id).enqueue(object : Callback<PropertyDetailResponse> {
            override fun onFailure(call: Call<PropertyDetailResponse>, t: Throwable) {
                Log.i("APIResponse", "Load details failed")
            }

            override fun onResponse(
                call: Call<PropertyDetailResponse>,
                response: Response<PropertyDetailResponse>
            ) {
                Log.i("APIResponse", response.body().toString())
                onDataLoad.onDetailLoad(response.body())
            }
        })
    }
}
