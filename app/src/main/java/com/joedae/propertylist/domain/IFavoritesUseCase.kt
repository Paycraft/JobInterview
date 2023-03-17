package com.joedae.propertylist.domain

import com.joedae.propertylist.data.db.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface IFavoritesUseCase {
    fun getFavoritesUpdates(): Flow<List<FavoritesEntity>>

    fun setFavorites(id: String)
}
