package com.joedae.propertylist.domain.implementation

import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.domain.IFavoritesUseCase
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(private val favoritesRepo: FavoritesRepo) :
    IFavoritesUseCase {

    override fun getFavoritesUpdates() = favoritesRepo.getFavoritesUpdates()

    override fun setFavorites(id: String) {
        favoritesRepo.setFavorites(id)
    }
}
