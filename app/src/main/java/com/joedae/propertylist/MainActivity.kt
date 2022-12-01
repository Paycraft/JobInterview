package com.joedae.propertylist

import android.os.Bundle
import android.util.Log
import android.util.Property
import androidx.appcompat.app.AppCompatActivity
import com.joedae.propertylist.adapter.CustomAdapter
import com.joedae.propertylist.api.PropertyService
import com.joedae.propertylist.data.*
import com.joedae.propertylist.databinding.ActivityMainBinding
import kotlinx.coroutines.processNextEventInCurrentThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
                var customAdapter = CustomAdapter(applicationContext, layoutInflater, response.body()!!.results)
                binding.list.adapter = customAdapter
            }
        })
    }
}