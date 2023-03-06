package com.joedae.propertylist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.MyApplicationTheme
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.data.db.SetFavorite
import com.joedae.propertylist.databinding.ActivityMainBinding
import com.joedae.propertylist.di.PropertyComponent
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase

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
//                MyApplicationTheme {
                    ListItem(it.results, setFavorite, propertyViewModel.favoritesData)
//                    PDP(property = it.results[0])
//                }
            }
            //Once we get Favorite flags we update the UI
            propertyViewModel.getFavorites()
        }
    }
}
