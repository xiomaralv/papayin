package com.fandango.papayin.presentation.modules.list.view

import com.fandango.papayin.data.source.response.MoviesResponse

interface IMoviesFragment {

    fun moviesError(exception: Exception)
    fun moviesSuccess(movies: MoviesResponse)
}