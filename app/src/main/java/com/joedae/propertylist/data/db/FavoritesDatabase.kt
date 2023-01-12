package com.joedae.propertylist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(FavoritesEntity::class)], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase {
            if (INSTANCE == null) {
                synchronized(FavoritesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoritesDatabase::class.java, "RoomFavorites_DB").build()
                }
            }
            return INSTANCE ?: throw AssertionError("There is absolutely no way that ContactDatabase is null")
        }
    }
}