package com.fandango.papayin.presentation.modules.list.presenter

import com.fandango.papayin.data.interactor.MovieInteractor
import com.fandango.papayin.data.interactor.MovieRepository
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.data.source.retrofit.CallbackService
import com.fandango.papayin.presentation.modules.list.view.MoviesFragment


class ListMoviesPresenterImpl(
    var moviesFragment: MoviesFragment
) : IListMoviesPresenter {

    private var moviesRepository: MovieRepository = MovieInteractor()

    override fun getMovies(page: Int) {

        moviesRepository.getMovies(
            page,
            object : CallbackService.SuccessCallback<MoviesResponse> {
                override fun onSuccess(response: MoviesResponse) {
                    moviesFragment.moviesSuccess(response)
                }
            }, object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    moviesFragment.moviesError(exception)
                }
            })
    }
}

