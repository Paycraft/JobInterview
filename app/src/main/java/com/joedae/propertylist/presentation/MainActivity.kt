package com.joedae.propertylist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.joedae.propertylist.adapter.CustomAdapter
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.data.db.SetFavorite
import com.joedae.propertylist.databinding.ActivityMainBinding
import com.joedae.propertylist.di.PropertyComponent
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val favoritesUseCase = FavoritesUseCase(
        favoritesRepo = FavoritesRepo(
            FavoritesDatabase.getInstance(PropertyComponent.getContext()).favoritesDao()
        )
    )

    var propertyViewModel =
        PropertyViewModel(getPropertyUseCase = GetPropertyUseCase(), favoritesUseCase)

    private val setFavorite: SetFavorite = object : SetFavorite {
        override fun onSetFavorite(id: String) {
            propertyViewModel.setFavorite(id)
        }
    }

    val adapter = CustomAdapter(listOf(), setFavorite)

    init {
        propertyViewModel.getListings()
        propertyViewModel.getFavoritesUpdates()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
        setContentView(binding.root)

        propertyViewModel.propertyData.observe(this) {
            adapter.properties = it.results
            // Once we get Favorite flags we update the UI
            propertyViewModel.getFavorites()
        }

        propertyViewModel.favoritesData.observe(this) { favoritesList ->
            // Set Favorite flags
            adapter.properties.map { listing ->
                listing.isFavorite = false
                favoritesList.map { favoritesEntity ->
                    if (listing.id == favoritesEntity.listingId) {
                        listing.isFavorite = true
                    }
                }
            }
            // Properties list now has Favorite flags set, update the UI
            binding.list.adapter?.notifyDataSetChanged()
        }
    }
}
