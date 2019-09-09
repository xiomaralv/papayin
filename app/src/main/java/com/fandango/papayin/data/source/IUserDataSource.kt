package com.fandango.papayin.data.source


import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.response.TrailersResponse
import com.fandango.papayin.data.source.retrofit.CallbackService


interface IUserDataSource {

    interface IUserRemoteSource {
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

        fun getTrailers(
            movieId: Int,
            successCallback: CallbackService.SuccessCallback<TrailersResponse>,
            errorCallback: CallbackService.ErrorCallback
        )
    }
}
