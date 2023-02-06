package com.joedae.propertylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyResponse
import com.joedae.propertylist.data.db.FavoritesEntity
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropertyViewModel(val getPropertyUseCase: GetPropertyUseCase, val favoritesUseCase: FavoritesUseCase): ViewModel() {

    private val _propertyData = MutableLiveData<PropertyResponse>()
    val propertyData: LiveData<PropertyResponse> = _propertyData
    private val _favoritesData = MutableLiveData<List<FavoritesEntity>>()
    val favoritesData: LiveData<List<FavoritesEntity>> = _favoritesData

    private val onDataLoad: OnDataLoad = object : OnDataLoad {
        override fun onDataLoad(propertyResponse: PropertyResponse) {
            _propertyData.value = propertyResponse
        }
    }

    init {
        getListings()
    }

    private fun getListings() {
        viewModelScope.launch { getPropertyUseCase.getProperties(onDataLoad) }
    }

    fun setFavorite(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoritesUseCase.setFavorites(id)
            }
        }
    }

    fun startListenToFavoritesChanges() {
        viewModelScope.launch { favoritesUseCase.getFavorites().collect { _favoritesData.value = it }
        }
    }
}