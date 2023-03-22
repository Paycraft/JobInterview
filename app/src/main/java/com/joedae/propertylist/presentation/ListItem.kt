package com.joedae.propertylist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.joedae.propertylist.R
import com.joedae.propertylist.data.PropertyResponse
import com.joedae.propertylist.data.db.CallBackActions
import com.joedae.propertylist.data.db.FavoritesEntity

@Composable
fun ListItem(
    propertyData: LiveData<PropertyResponse>,
    favoritesData: LiveData<List<FavoritesEntity>>,
    callBackActions: CallBackActions?,
    onDetailsClick: (id: String) -> Unit
) {
    val favoriteList by favoritesData.observeAsState()
    val propertiesList by propertyData.observeAsState()

    LazyColumn(
        modifier = Modifier.testTag(PropertyListTag),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(propertiesList?.results ?: emptyList()) { property ->
            property.isFavorite = false
            favoriteList?.map { favoritesEntity ->
                if (property.id == favoritesEntity.listingId) {
                    property.isFavorite = true
                }
            }
            Row {
                Text(property.listing.prices.buy.price.toString())
                Image(
                    painter = painterResource(R.drawable.main),
                    contentDescription = "main image",
                    modifier = Modifier.clickable { onDetailsClick(property.id) }

                )
                if (!property.isFavorite) {
                    Icon(
                        painter = painterResource(R.drawable.fav_border),
                        contentDescription = "favourite button",
                        modifier = Modifier
                            .testTag(NotFavoriteTag)
                            .clickable { callBackActions?.onSetFavorite(property.id) }
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.fav_filled),
                        contentDescription = "favourite button",
                        modifier = Modifier
                            .testTag(FavoriteTag)
                            .clickable { callBackActions?.onSetFavorite(property.id) }
                    )
                }
            }
            Column {
                Text(property.listing.localization.de.text.title)
                Text(property.listing.address.locality + property.listing.address.street)
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

const val PropertyListTag = "PropertyList"
const val FavoriteTag = "Favorite"
const val NotFavoriteTag = "NotFavorite"
