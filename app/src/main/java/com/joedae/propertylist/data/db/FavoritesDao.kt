package com.joedae.propertylist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
abstract class FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOne(favoritesEntity: FavoritesEntity)
}