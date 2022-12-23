package com.joedae.propertylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyResponse
import com.joedae.propertylist.domain.GetPropertyUseCase
import kotlinx.coroutines.launch

class PropertyViewModel: ViewModel() {
    private val getPropertyUseCase: GetPropertyUseCase = GetPropertyUseCase()

    private val _propertyData = MutableLiveData<PropertyResponse>()
    val propertyData: LiveData<PropertyResponse> = _propertyData
    val onDataLoad: OnDataLoad = object : OnDataLoad {
        override fun onDataLoad(propertyResponse: PropertyResponse) {
            _propertyData.value = propertyResponse
        }
    }

    fun getListings() {
        viewModelScope.launch { getPropertyUseCase.getProperties(onDataLoad) }
    }

    fun getListingById(id: Int) {
        getPropertyUseCase.getPropertyById(id)
    }
}