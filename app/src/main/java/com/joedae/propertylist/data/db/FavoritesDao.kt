package com.joedae.propertylist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOne(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM Watchlist")
    protected abstract fun loadFavoriteIdsFlow(): Flow<List<FavoritesEntity>>

    fun loadFavoriteIdsDistinctFlow() = loadFavoriteIdsFlow().distinctUntilChanged()
}