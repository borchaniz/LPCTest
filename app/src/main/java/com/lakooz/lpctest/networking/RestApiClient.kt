package com.lakooz.lpctest.networking

import com.lakooz.lpctest.model.Pot
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder




object RestApiClient {

    private const val BASE_URL = "https://recrutement.lepotcommuntest.fr/2019/recruiting/"

    val gson = GsonBuilder()
        .setDateFormat("dd-MM-yyyy  HH:mm:ss")
        .create()

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(EndPoints::class.java)

    fun getPots(): Single<List<Pot>> {
        return client.getPots()
    }

    fun createPot(category: Int): Single<Pot> {
        return client.createPot(category)
    }
}