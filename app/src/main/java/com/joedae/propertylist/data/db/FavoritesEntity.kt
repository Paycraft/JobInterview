package com.joedae.propertylist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Watchlist")
data class FavoritesEntity(
    @ColumnInfo(name = "ListingId") var listingId: String = "",
    @ColumnInfo(name = "_id") @PrimaryKey(autoGenerate = true) var Id: Long = 0L
)
