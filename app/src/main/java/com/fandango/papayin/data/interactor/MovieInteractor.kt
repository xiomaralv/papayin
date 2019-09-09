package com.fandango.papayin.data.interactor

import com.fandango.papayin.data.source.retrofit.CallbackService
import com.fandango.papayin.data.source.IUserDataSource
import com.fandango.papayin.data.source.RemoteUserSource
import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.response.TrailersResponse

class MovieInteractor : MovieRepository {


    private var iUserRemoteSource: IUserDataSource.IUserRemoteSource = RemoteUserSource()

    override fun getMovies(
        page: Int,
        successCallback: CallbackService.SuccessCallback<MoviesResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {
        iUserRemoteSource.getMovies(
            page,
            object : CallbackService.SuccessCallback<MoviesResponse> {
                override fun onSuccess(response: MoviesResponse) {
                    successCallback.onSuccess(response)
                }
            },
            object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    errorCallback.onError(exception)
                }
            })
    }

    override fun getMovieDetail(
        id: Int,
        successCallback: CallbackService.SuccessCallback<MoviesDetailResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {
        iUserRemoteSource.getMovieDetail(
            id,
            object : CallbackService.SuccessCallback<MoviesDetailResponse> {
                override fun onSuccess(response: MoviesDetailResponse) {
                    successCallback.onSuccess(response)
                }
            },
            object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    errorCallback.onError(exception)
                }
            })
    }

    override fun getTrailer(
        id: Int,
        successCallback: CallbackService.SuccessCallback<TrailersResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {
        iUserRemoteSource.getTrailers(
            id,
            object : CallbackService.SuccessCallback<TrailersResponse> {
                override fun onSuccess(response: TrailersResponse) {
                    successCallback.onSuccess(response)
                }
            },
            object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    errorCallback.onError(exception)
                }
            })
    }
}