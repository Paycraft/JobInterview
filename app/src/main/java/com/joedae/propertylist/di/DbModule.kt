package com.joedae.propertylist.di

import android.app.Application
import com.joedae.propertylist.api.PropertyService
import com.joedae.propertylist.data.FavoritesRepo
import com.joedae.propertylist.data.IFavoritesRepo
import com.joedae.propertylist.data.IPropertyRepo
import com.joedae.propertylist.data.PropertyRepo
import com.joedae.propertylist.data.db.FavoritesDao
import com.joedae.propertylist.data.db.FavoritesDatabase
import com.joedae.propertylist.domain.IFavoritesUseCase
import com.joedae.propertylist.domain.IGetPropertyUseCase
import com.joedae.propertylist.domain.implementation.FavoritesUseCase
import com.joedae.propertylist.domain.implementation.GetPropertyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideFavoritesDao(application: Application): FavoritesDao {
        return FavoritesDatabase.getInstance(application).favoritesDao()
    }
}
