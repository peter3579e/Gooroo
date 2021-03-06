package com.peter.gooroo.network

import com.peter.gooroo.BuildConfig
import com.peter.gooroo.data.Combine
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://exorciser-chatbot.herokuapp.com/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = when (BuildConfig.LOGGER_VISIABLE) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

/**
 * A public interface that exposes the [getNumberValue] and [postTenNumber] methods
 */
interface GoorooApiService {

    /**
     * Returns a Coroutine [Deferred] [Process] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "process" endpoint will be requested with the GET HTTP method
     */

    @GET("process")
    suspend fun getNumberValue(
            @Query("input") input: Int? = null
    ): Process

    /**
     * Returns a Coroutine [Deferred] [Combine] which can be fetched with await() if in a Coroutine scope.
     * The @POST annotation indicates that the "combine" endpoint will be requested with the POST HTTP method
     * The @Body annotation indicates that it will be added [processed_value] to the body of the POST HTTP method
     */

    @POST("combine")
    suspend fun postTenNumber(
            @Body processed_value: PostTen? = null
    ): Combine

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GoorooApi {
    val retrofitService: GoorooApiService by lazy { retrofit.create(GoorooApiService::class.java) }
}