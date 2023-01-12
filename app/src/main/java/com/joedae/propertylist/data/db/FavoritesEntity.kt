package com.joedae.propertylist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Watchlist")
data class FavoritesEntity(@ColumnInfo(name = "_id") @PrimaryKey(autoGenerate = false) var advId: Long = 0L,
                           @ColumnInfo(name = "ListingId") var listingId: String = "")