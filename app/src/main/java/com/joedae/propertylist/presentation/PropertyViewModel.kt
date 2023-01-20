package com.joedae.propertylist.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.OnDataLoad
import com.joedae.propertylist.data.PropertyResponse
import com.joedae.propertylist.data.db.FavoritesDao
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.data.db.FavoritesEntity
import com.joedae.propertylist.domain.GetFavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase
import kotlinx.coroutines.launch

class PropertyViewModel: ViewModel() {
    private val getPropertyUseCase: GetPropertyUseCase = GetPropertyUseCase()

    private val _propertyData = MutableLiveData<PropertyResponse>()
    val propertyData: LiveData<PropertyResponse> = _propertyData
    private val _favoritesData = MutableLiveData<List<FavoritesEntity>>()
    val favoritesData: LiveData<List<FavoritesEntity>> = _favoritesData

    private val onDataLoad: OnDataLoad = object : OnDataLoad {
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

    fun startListenToFavoritesChanges(context: Context) {
        val favoritesRepo = FavoritesRepo(FavoritesDatabase.getInstance(context).favoritesDao())
        val getFavoritesUseCase = GetFavoritesUseCase(favoritesRepo)
        viewModelScope.launch { getFavoritesUseCase.getFavorites().collect { _favoritesData.value = it } }
    }
}