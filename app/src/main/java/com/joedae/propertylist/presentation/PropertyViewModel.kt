package com.joedae.propertylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyDetailResponse
import com.joedae.propertylist.data.PropertyResponse
import com.joedae.propertylist.data.db.FavoritesEntity
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropertyViewModel(
    val getPropertyUseCase: GetPropertyUseCase,
    val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    private val _propertyData = MutableLiveData<PropertyResponse>()
    val propertyData: LiveData<PropertyResponse> = _propertyData
    private val _favoritesData = MutableLiveData<List<FavoritesEntity>>()
    val favoritesData: LiveData<List<FavoritesEntity>> = _favoritesData
    private val _detailData = MutableLiveData<PropertyDetailResponse>()
    val detailData: LiveData<PropertyDetailResponse> = _detailData

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    private val onDataLoad: OnDataLoad = object : OnDataLoad {
        override fun onDataLoad(propertyResponse: PropertyResponse?) {
            _propertyData.value = propertyResponse
        }

        override fun onDetailLoad(propertyDetailResponse: PropertyDetailResponse?) {
            _loading.value = false
            _detailData.value = propertyDetailResponse
        }
    }

    fun getListings() {
        viewModelScope.launch { getPropertyUseCase.getProperties(onDataLoad) }
    }

    fun getDetail(id: String) {
        _loading.value = true
        viewModelScope.launch { getPropertyUseCase.getPropertyById(id, onDataLoad) }
    }

    fun setFavorite(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoritesUseCase.setFavorites(id)
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            favoritesUseCase.getFavorites().collect { _favoritesData.value = it }
        }
    }

    fun getFavoritesUpdates() {
        viewModelScope.launch {
            favoritesUseCase.getFavoritesUpdates().collect { _favoritesData.value = it }
        }
    }

}
