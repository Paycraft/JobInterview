package com.joedae.propertylist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.data.db.SetFavorite
import com.joedae.propertylist.databinding.ActivityMainBinding
import com.joedae.propertylist.di.PropertyComponent
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase

//Compose
import androidx.activity.compose.setContent
import com.joedae.propertylist.data.*

class MainActivity : ComponentActivity() {
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

    init {
        propertyViewModel.getListings()
        propertyViewModel.getFavoritesUpdates()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        propertyViewModel.propertyData.observe(this) {
            setContent {
//                ListItem(it.results, setFavorite)
                MyApplicationTheme {
                    PDP(property = it.results[0])
                }
            }
            //Once we get Favorite flags we update the UI
            propertyViewModel.getFavorites()
        }

        propertyViewModel.favoritesData.observe(this) { favoritesList ->
            // Set Favorite flags
            //TODO
//             adapter.properties.map { listing ->
//                listing.isFavorite = false
//                favoritesList.map { favoritesEntity ->
//                    if (listing.id == favoritesEntity.listingId) {
//                        listing.isFavorite = true
//                    }
//                }
//            }

        }
    }
}
