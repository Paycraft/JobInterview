package com.joedae.propertylist.data

import com.joedae.propertylist.data.db.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface IFavoritesRepo {
    fun getFavoritesUpdates(): Flow<List<FavoritesEntity>>
    fun setFavorites(id: String)
}
