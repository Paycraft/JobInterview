package com.joedae.propertylist.domain.implementation

import com.joedae.propertylist.data.FavoritesRepo

class FavoritesUseCase(val favoritesRepo: FavoritesRepo) {

    fun getFavoritesUpdates() = favoritesRepo.getFavoritesUpdates()

    fun setFavorites(id: String) {
        favoritesRepo.setFavorites(id)
    }
}
