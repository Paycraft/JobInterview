package com.joedae.propertylist.di

import com.joedae.propertylist.data.*
import com.joedae.propertylist.data.db.FavoritesEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.InputStreamReader

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepoModule::class]
)
@Module
object TestRepoModule {
    private fun getOfflineResponse(path: String): String {
        return this.javaClass.classLoader?.getResourceAsStream(path)?.let {
            val reader = InputStreamReader(it)
            val content = reader.readText()
            reader.close()
            content
        } ?: ""
    }

    @Provides
    fun providePropertyRepo(): IPropertyRepo {
        val moshi: Moshi = Moshi.Builder().build()
        return object : IPropertyRepo {
            override fun getListings(onDataLoad: OnDataLoad) {
                val jsonAdapter: JsonAdapter<PropertyResponse> =
                    moshi.adapter(PropertyResponse::class.java)
                val propertyResponse =
                    jsonAdapter.fromJson(getOfflineResponse("propertyListResponse.json"))
                onDataLoad.onDataLoad(propertyResponse)
            }

            override fun getListingById(id: String, onDataLoad: OnDataLoad) {
                val jsonAdapter: JsonAdapter<PropertyDetailResponse> =
                    moshi.adapter(PropertyDetailResponse::class.java)
                val propertyDetailResponse =
                    jsonAdapter.fromJson(getOfflineResponse("propertyResponse.json"))
                onDataLoad.onDetailLoad(propertyDetailResponse)
            }
        }
    }

    @Provides
    fun provideFavoritesRepo(): IFavoritesRepo {
        return object : IFavoritesRepo {
            override fun getFavoritesUpdates(): Flow<List<FavoritesEntity>> {
                val favoritesEntity = FavoritesEntity("104123262")
                return flowOf(listOf(favoritesEntity))
            }

            override fun setFavorites(id: String) {
            }
        }
    }
}
