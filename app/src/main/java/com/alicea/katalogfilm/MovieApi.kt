package com.alicea.katalogfilm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApi {
    @GET("popular?&region=ID")
    fun getPopularMovies(@Header("Authorization") authHeader: String?) : Call<Movies>
}