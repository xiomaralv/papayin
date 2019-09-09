package com.fandango.papayin.presentation.modules.trailer.view

import com.fandango.papayin.data.model.Trailer

interface ITrailerActivity {

    fun trailerError(exception: Exception)
    fun trailersSuccess(trailers: Trailer)
}