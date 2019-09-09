package com.fandango.papayin.presentation.modules.detail.view

import com.fandango.papayin.data.source.response.MoviesDetailResponse

interface IDetailMovieFragment {
    fun movieError(exception: Exception)
    fun movieSuccess(movie: MoviesDetailResponse)
}