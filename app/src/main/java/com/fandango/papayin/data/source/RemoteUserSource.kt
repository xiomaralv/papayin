package com.fandango.papayin.data.source

import com.fandango.papayin.Constants
import com.fandango.papayin.data.source.api.IUserApi
import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.retrofit.CallbackService
import com.fandango.papayin.data.source.retrofit.ConectionRestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.response.TrailersResponse

class RemoteUserSource : IUserDataSource.IUserRemoteSource {

    private lateinit var iUserApi: IUserApi
    private val LANGUAGE = "es-PE"

    override fun getMovies(
        page: Int,
        successCallback: CallbackService.SuccessCallback<MoviesResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {
        iUserApi = ConectionRestApi.client.create(IUserApi::class.java)
        val call1 = iUserApi.getPopularMovies(Constants.API_KEY, LANGUAGE, page)
        call1.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val moviesResponse = response.body()
                    if (moviesResponse?.movies != null) {
                        successCallback.onSuccess(moviesResponse)
                    } else {
                        errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                    }
                } else {
                    errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
            }
        })
    }

    override fun getMovieDetail(
        id: Int,
        successCallback: CallbackService.SuccessCallback<MoviesDetailResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {

        iUserApi = ConectionRestApi.client.create(IUserApi::class.java)
        val call1 = iUserApi.getMovieDetails(id, Constants.API_KEY, LANGUAGE)
        call1.enqueue(object : Callback<MoviesDetailResponse> {
            override fun onResponse(
                call: Call<MoviesDetailResponse>,
                response: Response<MoviesDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val movieDetailResponse = response.body()
                    if (movieDetailResponse != null) {
                        successCallback.onSuccess(movieDetailResponse)
                    } else {
                        errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                    }
                } else {
                    errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                }
            }

            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
            }
        })
    }

    override fun getTrailers(
        movieId: Int,
        successCallback: CallbackService.SuccessCallback<TrailersResponse>,
        errorCallback: CallbackService.ErrorCallback
    ) {
        iUserApi = ConectionRestApi.client.create(IUserApi::class.java)
        val call1 = iUserApi.getTrailers(movieId, Constants.API_KEY)
        call1.enqueue(object : Callback<TrailersResponse> {
            override fun onResponse(
                call: Call<TrailersResponse>,
                response: Response<TrailersResponse>
            ) {
                if (response.isSuccessful) {
                    val trailersResponse = response.body()
                    if (trailersResponse?.trailers != null) {
                        successCallback.onSuccess(trailersResponse)
                    } else {
                        errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                    }
                } else {
                    errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
                }
            }

            override fun onFailure(call: Call<TrailersResponse>, t: Throwable) {
                errorCallback.onError(Exception("Ocurrio un error, vuelva a intentarlo."))
            }
        })
    }

}
