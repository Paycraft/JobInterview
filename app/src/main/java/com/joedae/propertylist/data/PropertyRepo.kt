package com.joedae.propertylist.data

import android.util.Log
import com.joedae.propertylist.api.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PropertyRepo @Inject constructor(private val propertyService: PropertyService): IPropertyRepo {
    override fun getListings(onDataLoad: OnDataLoad) {
        propertyService.getProperty().enqueue(object : Callback<PropertyResponse> {
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.i("APIResponse", "Load all listings failed")
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

    override fun getListingById(id: String, onDataLoad: OnDataLoad) {
        propertyService.getPropertyByID(id).enqueue(object : Callback<PropertyDetailResponse> {
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
