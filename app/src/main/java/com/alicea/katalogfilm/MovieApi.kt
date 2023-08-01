package com.alicea.katalogfilm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?&region=ID")
    fun getPopularMovies(@Header("Authorization") authHeader: String?) : Call<Movies>

    @GET("search/movie?")
    fun getSearchMovies(
        @Header("Authorization") authHeader: String?,
        @Query("query") query: String) : Call<Movies>
}