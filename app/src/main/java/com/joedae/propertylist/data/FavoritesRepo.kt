package com.joedae.propertylist.data

import com.joedae.propertylist.data.db.FavoritesDao
import com.joedae.propertylist.data.db.FavoritesEntity
import kotlinx.coroutines.flow.Flow

class FavoritesRepo(val favoritesDao: FavoritesDao) {


    fun getFavorites(): Flow<List<FavoritesEntity>> {
        return favoritesDao.loadFavoriteIdsDistinctFlow()
    }

    fun setFavorites(id: String) {
        var entity = favoritesDao.get(id)
        if (entity == null) {
            favoritesDao.insertOne(favoritesEntity = FavoritesEntity(id))
        } else {
            favoritesDao.delete(entity)
        }
    }
}