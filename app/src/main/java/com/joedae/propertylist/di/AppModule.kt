package com.joedae.propertylist.di

import android.app.Application
import com.joedae.propertylist.api.PropertyService
import com.joedae.propertylist.data.FavoritesRepo
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
object AppModule {
    @Provides
    @Singleton
    fun provideFavoritesDao(application: Application): FavoritesDao {
        return FavoritesDatabase.getInstance(application).favoritesDao()
    }

    @Provides
    @Singleton
    fun providePropertyService(): PropertyService {
        return Retrofit.Builder()
            .baseUrl("https://private-9f1bb1-homegate3.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PropertyService::class.java)
    }

    @Provides
    fun providePropertyRepo(propertyService: PropertyService): PropertyRepo {
        return PropertyRepo(propertyService)
    }

    @Provides
    fun provideFavoritesRepo(favoritesDao: FavoritesDao): FavoritesRepo {
        return FavoritesRepo(favoritesDao)
    }

    @Provides
    fun provideGetPropertyUseCase(propertyRepo: PropertyRepo): IGetPropertyUseCase {
        return GetPropertyUseCase(propertyRepo)
    }

    @Provides
    fun provideFavoritesUseCase(favoritesRepo: FavoritesRepo): IFavoritesUseCase {
        return FavoritesUseCase(favoritesRepo)
    }
}
