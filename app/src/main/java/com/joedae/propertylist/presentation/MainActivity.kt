package com.joedae.propertylist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.MyApplicationTheme
import com.joedae.propertylist.data.db.CallBackActions
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.databinding.ActivityMainBinding
import com.joedae.propertylist.di.PropertyComponent
import com.joedae.propertylist.domain.FavoritesUseCase
import com.joedae.propertylist.domain.GetPropertyUseCase

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private val favoritesUseCase = FavoritesUseCase(
        favoritesRepo = FavoritesRepo(
            FavoritesDatabase.getInstance(PropertyComponent.getContext()).favoritesDao()
        )
    )

    var propertyViewModel =
        PropertyViewModel(getPropertyUseCase = GetPropertyUseCase(), favoritesUseCase)

    private val callBackActions: CallBackActions = object : CallBackActions {
        override fun onSetFavorite(id: String) {
            propertyViewModel.setFavorite(id)
        }

        override fun openPDP(id: String) {
            propertyViewModel.getDetail(id)
            setContent {
                MyApplicationTheme {
                    PDP(propertyViewModel.loading, propertyViewModel.detailData)
                }
            }
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
                MyApplicationTheme {
                    ListItem(it.results, callBackActions, propertyViewModel.favoritesData)
                }
            }
            //Once we get Favorite flags we update the UI
            propertyViewModel.getFavorites()
        }
    }
}
