package com.joedae.propertylist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joedae.propertylist.data.MyApplicationTheme
import com.joedae.propertylist.data.db.CallBackActions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val propertyViewModel: PropertyViewModel by viewModels()

    private val callBackActions: CallBackActions = object : CallBackActions {
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

        propertyViewModel.propertyData.observe(this) { propertyResponse ->
            setContent {
                MyApplicationTheme {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "listItem") {
                        composable("listItem") {
                            ListItem(propertyResponse.results,
                                callBackActions,
                                propertyViewModel.favoritesData,
                                onDetailsClick = { id ->
                                    propertyViewModel.getDetail(id)
                                    navController.navigate("details")
                                })
                        }
                        composable("details") {
                            PDP(propertyViewModel.loading,
                                propertyViewModel.detailData,
                                onNavigateUp = {
                                    navController.popBackStack()
                                })
                        }
                    }
                }
            }
        }
    }
}
