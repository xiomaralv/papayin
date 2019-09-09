package com.fandango.papayin.data.source.api

import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.response.TrailersResponse
import retrofit2.Call
import retrofit2.http.*

interface IUserApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>


    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String,  @Query("language") language: String ): Call<MoviesDetailResponse>

    @GET("movie/{id}/videos")
    fun getTrailers(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TrailersResponse>
}