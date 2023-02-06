package com.joedae.propertylist.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.joedae.propertylist.adapter.CustomAdapter
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.Property
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.data.db.SetFavorite
import com.joedae.propertylist.databinding.ActivityMainBinding
import com.joedae.propertylist.di.PropertyComponent
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listings: MutableList<List<Property>> = mutableListOf()

    val favoritesUseCase = FavoritesUseCase(favoritesRepo = FavoritesRepo(FavoritesDatabase.getInstance(PropertyComponent.getContext()).favoritesDao()))
    var propertyViewModel = PropertyViewModel(getPropertyUseCase = GetPropertyUseCase(), favoritesUseCase)

    private val setFavorite: SetFavorite = object : SetFavorite {
        override fun onSetFavorite(id: String) {
            propertyViewModel.setFavorite(id)
        }
    }

    init {
        propertyViewModel.startListenToFavoritesChanges()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.list.layoutManager = LinearLayoutManager(this)
        val view = binding.root
        setContentView(view)

        propertyViewModel.propertyData.observe(this) {
            val adapter = CustomAdapter(it.results, setFavorite)
            Toast.makeText(this, adapter.itemCount.toString(), Toast.LENGTH_SHORT).show()

            binding.list.adapter = adapter
            listings = mutableListOf(it.results)
        }

        propertyViewModel.favoritesData.observe(this) { favoritesList ->
            listings.map {
                it.map { listing ->
                    favoritesList.map { favoritesEntity ->
                        if (listing.id == favoritesEntity.listingId) {
                            Toast.makeText(this, listing.id, Toast.LENGTH_SHORT).show()
                            listing.isFavorite = true
                        }
                    }
                }
            }
        }
    }
}