package com.fandango.papayin.data.interactor

import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.response.TrailersResponse
import com.fandango.papayin.data.source.retrofit.CallbackService

interface MovieRepository {
    fun getMovies(
        page: Int,
        successCallback: CallbackService.SuccessCallback<MoviesResponse>,
        errorCallback: CallbackService.ErrorCallback
    )

    fun getMovieDetail(
        id: Int,
        successCallback: CallbackService.SuccessCallback<MoviesDetailResponse>,
        errorCallback: CallbackService.ErrorCallback
    )

    fun getTrailer(
        id: Int,
        successCallback: CallbackService.SuccessCallback<TrailersResponse>,
        errorCallback: CallbackService.ErrorCallback
    )
}