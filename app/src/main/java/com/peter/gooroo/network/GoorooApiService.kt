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
 * A public interface that exposes the [getMarketingHots], [getProductList], [getUserProfile],
 * [userSignIn], [checkoutOrder] methods
 */
interface GoorooApiService {

    @GET("process")
    suspend fun getNumberValue(
        @Query("input") input: Int? = null
    ):Process


    @POST("combine")
    suspend fun postTenNumber(
    @Body processed_value: PostTen? = null
    ):Combine

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GoorooApi {
    val retrofitService : GoorooApiService  by lazy { retrofit.create(GoorooApiService::class.java) }
}