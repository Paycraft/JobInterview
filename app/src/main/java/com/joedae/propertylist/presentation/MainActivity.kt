package com.joedae.propertylist.presentation

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.joedae.propertylist.adapter.CustomAdapter
import com.joedae.propertylist.data.Property
import com.joedae.propertylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var propertyViewModel: PropertyViewModel
    private var listings: MutableList<List<Property>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        propertyViewModel = PropertyViewModel()
        propertyViewModel.startListenToFavoritesChanges(this)
        propertyViewModel.getListings()

        propertyViewModel.propertyData.observe(this) {
            val customerAdapter = CustomAdapter(this.applicationContext, layoutInflater, it.results)
            binding.list.adapter = customerAdapter
            listings = mutableListOf(it.results)
        }

        propertyViewModel.favoritesData.observe(this) { favoritesList ->
            listings.map {
                it.map { listing ->
                    favoritesList.map { favoritesEntity ->
                        if (listing.id == favoritesEntity.listingId) {
                            listing.isFavorite = true
                        }
                    }
                }
            }
        }

        return super.onCreateView(name, context, attrs)
    }
}