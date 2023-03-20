package com.joedae.propertylist.di

import android.app.Application
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
    fun providePropertyRepo(): PropertyRepo {
        return PropertyRepo()
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
