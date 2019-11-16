package com.lakooz.lpctest.networking

import com.lakooz.lpctest.model.Pot
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPoints {
    @GET("/get-pots")
    fun getPots(): Single<List<Pot>>

    @POST("create-pot")
    fun createPot(@Body category: Int): Single<Pot>
}