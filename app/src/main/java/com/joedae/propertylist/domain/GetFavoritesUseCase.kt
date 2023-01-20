package com.joedae.propertylist.domain

import com.joedae.propertylist.data.FavoritesRepo


class GetFavoritesUseCase(val favoritesRepo: FavoritesRepo) {


    fun getFavorites()=favoritesRepo.getFavorites()
}