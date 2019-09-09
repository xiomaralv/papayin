package com.fandango.papayin.presentation.modules.trailer.presenter

import com.fandango.papayin.data.interactor.MovieInteractor
import com.fandango.papayin.data.interactor.MovieRepository
import com.fandango.papayin.data.source.response.TrailersResponse
import com.fandango.papayin.data.source.retrofit.CallbackService
import com.fandango.papayin.presentation.modules.trailer.view.TrailerActivity

class TrailerMoviePresenterImpl(
    var trailerActivity: TrailerActivity
) : ITrailerMoviePresenter {

    private var moviesRepository: MovieRepository = MovieInteractor()

    override fun getTrailer(movieId: Int) {
        moviesRepository.getTrailer(
            movieId,
            object : CallbackService.SuccessCallback<TrailersResponse> {
                override fun onSuccess(response: TrailersResponse) {
                    trailerActivity.trailersSuccess(response.trailers!![0])
                }
            }, object : CallbackService.ErrorCallback {
                override fun onError(exception: Exception) {
                    trailerActivity.trailerError(exception)
                }
            })
    }
}