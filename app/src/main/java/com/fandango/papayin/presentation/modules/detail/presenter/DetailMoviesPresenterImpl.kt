package com.fandango.papayin.presentation.modules.list.presenter

import com.fandango.papayin.data.interactor.MovieInteractor
import com.fandango.papayin.data.interactor.MovieRepository
import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.data.source.retrofit.CallbackService
import com.fandango.papayin.presentation.modules.detail.view.DetailMovieFragment


class DetailMoviesPresenterImpl(
    var movieDetailFragment: DetailMovieFragment
) : IDetailMoviesPresenter {

    private var moviesRepository: MovieRepository = MovieInteractor()

    override fun getMovieDetail(idMovie: Int) {

        moviesRepository.getMovieDetail(
            idMovie,
            object : CallbackService.SuccessCallback<MoviesDetailResponse> {
                override fun onSuccess(response: MoviesDetailResponse) {
                    movieDetailFragment.movieSuccess(response)
                }
            }, object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    movieDetailFragment.movieError(exception)
                }
            })
    }
}

