package com.joedae.propertylist.di

import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.IFavoritesRepo
import com.joedae.propertylist.data.IPropertyRepo
import com.joedae.propertylist.data.PropertyRepo
import com.joedae.propertylist.domain.IFavoritesUseCase
import com.joedae.propertylist.domain.IGetPropertyUseCase
import com.joedae.propertylist.domain.implementation.FavoritesUseCase
import com.joedae.propertylist.domain.implementation.GetPropertyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPropertyUseCase(propertyRepo: IPropertyRepo): IGetPropertyUseCase {
        return GetPropertyUseCase(propertyRepo)
    }

    @Provides
    fun provideFavoritesUseCase(favoritesRepo: IFavoritesRepo): IFavoritesUseCase {
        return FavoritesUseCase(favoritesRepo)
    }
}
