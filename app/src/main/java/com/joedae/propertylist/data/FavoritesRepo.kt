package com.joedae.propertylist.data

import com.joedae.propertylist.data.db.FavoritesDao
import com.joedae.propertylist.data.db.FavoritesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepo @Inject constructor(private val favoritesDao: FavoritesDao) {
    fun getFavoritesUpdates(): Flow<List<FavoritesEntity>> {
        return favoritesDao.loadFavoriteUpdatesFlow()
    }

    fun setFavorites(id: String) {
        val entity = favoritesDao.get(id)
        if (entity == null) {
            favoritesDao.insertOne(favoritesEntity = FavoritesEntity(id))
        } else {
            favoritesDao.delete(entity)
        }
    }
}
