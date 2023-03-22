package com.joedae.propertylist

import com.joedae.propertylist.api.PropertyService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStreamReader
import java.net.HttpURLConnection

class PropertyListParseTest {
    private lateinit var propertyService: PropertyService
    private lateinit var mockWebServer: MockWebServer

    private fun getOfflineResponse(): String {
        return this.javaClass.classLoader?.getResourceAsStream("propertyListResponse.json")?.let {
            val reader = InputStreamReader(it)
            val content = reader.readText()
            reader.close()
            content
        } ?: ""
    }

    @Before
    fun initRetrofit() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        propertyService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PropertyService::class.java)
    }

    @Test
    fun gotHttpOk() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(getOfflineResponse())
        )

        val call = propertyService.getProperty()
        val response = runBlocking { call.awaitResponse() }
        response.isSuccessful.shouldBeTrue()
        response.code() shouldBeEqualTo HttpURLConnection.HTTP_OK
        response.errorBody().shouldBeNull()
        response.body().shouldNotBeNull()
        response.body()?.let {
            it.results.size shouldBeEqualTo 9
        }
    }

    @Test
    fun gotHttpError() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND).setBody(
                """
                    {
                    "message": "Something went wrong"
                    }
                """.trimIndent()
            )
        )

        val call = propertyService.getProperty()
        val response = runBlocking { call.awaitResponse() }
        response.isSuccessful.shouldBeFalse()
        response.code() shouldBeEqualTo HttpURLConnection.HTTP_NOT_FOUND
        response.body().shouldBeNull()
        response.errorBody().shouldNotBeNull()
    }
}
