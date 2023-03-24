package com.joedae.propertylist.domain.implementation

import com.joedae.propertylist.data.IFavoritesRepo
import com.joedae.propertylist.data.db.FavoritesEntity
import com.joedae.propertylist.domain.IFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(private val favoritesRepo: IFavoritesRepo) :
    IFavoritesUseCase {

    override fun getFavoritesUpdates(): Flow<List<FavoritesEntity>> {
        return favoritesRepo.getFavoritesUpdates()
    }

    override fun setFavorites(id: String) {
        favoritesRepo.setFavorites(id)
    }
}
