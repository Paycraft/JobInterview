package com.joedae.propertylist.domain

import com.joedae.propertylist.data.FavoritesRepo


class FavoritesUseCase(val favoritesRepo: FavoritesRepo) {
    fun getFavorites() = favoritesRepo.getFavorites()

    fun getFavoritesUpdates() = favoritesRepo.getFavoritesUpdates()

    fun setFavorites(id: String) {
        favoritesRepo.setFavorites(id)
    }
}