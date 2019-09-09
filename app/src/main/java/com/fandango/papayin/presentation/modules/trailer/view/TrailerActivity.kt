package com.fandango.papayin.presentation.modules.trailer.view

import android.os.Bundle
import android.widget.Toast
import com.fandango.papayin.R
import com.fandango.papayin.data.model.Trailer
import com.fandango.papayin.presentation.modules.trailer.presenter.ITrailerMoviePresenter
import com.fandango.papayin.presentation.modules.trailer.presenter.TrailerMoviePresenterImpl
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_trailer.*


class TrailerActivity : YouTubeBaseActivity(),
    ITrailerActivity {
    companion object {
        @JvmStatic
        val MOVIE_ID = "MOVIE_ID"
        const val YOUTUBE_API_KEY: String = "AIzaSyB_IBk4_V8cM8G5xz3GZiZtLj9_ezw0qc0"
    }

    private var trailerMoviePresenter: ITrailerMoviePresenter = TrailerMoviePresenterImpl(this)
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    private var idMovie: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        intent?.let {
            idMovie = intent.getIntExtra(MOVIE_ID, 0)
        }
        trailerMoviePresenter.getTrailer(idMovie)
    }

    private fun initUI(videoId: String) {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youtubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubePlayer?.loadVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Algo salió mal !! ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        youtubePlayer.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
    }

    override fun trailersSuccess(trailer: Trailer) {
        trailer.key?.let { initUI(it) }
    }

    override fun trailerError(exception: Exception) {
        Toast.makeText(applicationContext, exception.message, Toast.LENGTH_SHORT)
            .show()
    }
}