package com.joedae.propertylist.di

import com.joedae.propertylist.api.PropertyService
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.IFavoritesRepo
import com.joedae.propertylist.data.IPropertyRepo
import com.joedae.propertylist.data.PropertyRepo
import com.joedae.propertylist.data.db.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun providePropertyRepo(propertyService: PropertyService): IPropertyRepo {
        return PropertyRepo(propertyService)
    }

    @Provides
    fun provideFavoritesRepo(favoritesDao: FavoritesDao): IFavoritesRepo {
        return FavoritesRepo(favoritesDao)
    }
}
