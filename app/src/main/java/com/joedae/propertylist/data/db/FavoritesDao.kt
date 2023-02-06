package com.joedae.propertylist.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOne(favoritesEntity: FavoritesEntity)

    @Query("Select * From Watchlist")
    protected abstract fun loadFavoriteIdsFlow(): Flow<List<FavoritesEntity>>

    fun loadFavoriteIdsDistinctFlow() = loadFavoriteIdsFlow().distinctUntilChanged()

    @Query("Select * From Watchlist where ListingId=:listingId")
    abstract fun get(listingId: String): FavoritesEntity?

    @Delete
    abstract fun delete(favoritesEntity: FavoritesEntity)
}