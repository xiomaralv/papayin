package com.fandango.papayin.presentation.modules.detail.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandango.papayin.Constants
import com.fandango.papayin.R
import com.fandango.papayin.data.model.ProductionCompanies
import com.fandango.papayin.data.source.response.MoviesDetailResponse
import com.fandango.papayin.presentation.BaseActivity
import com.fandango.papayin.presentation.modules.list.presenter.DetailMoviesPresenterImpl
import com.fandango.papayin.presentation.modules.list.presenter.IDetailMoviesPresenter
import com.fandango.papayin.presentation.modules.trailer.view.TrailerActivity
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_ID = "id"


class DetailMovieFragment : Fragment(), IDetailMovieFragment {

    private var id: Int? = null

    private var moviesDetailPresenter: IDetailMoviesPresenter = DetailMoviesPresenterImpl(this)
    var myCustomAdapter: MyMoviesDetailRecyclerViewAdapter
    val listProduction: MutableList<ProductionCompanies> = ArrayList()

    init {
        myCustomAdapter = MyMoviesDetailRecyclerViewAdapter(listProduction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)

        }

        moviesDetailPresenter.getMovieDetail(id!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_movie, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                listProductionRecycler.layoutManager = LinearLayoutManager(context)
                listProductionRecycler.adapter = myCustomAdapter
            }
        }


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { view ->
            val intent = Intent(this@DetailMovieFragment.context, TrailerActivity::class.java)
            intent.putExtra(TrailerActivity.MOVIE_ID, id)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }

    override fun movieError(exception: Exception) {
        progressbarDetailMovie.visibility = View.GONE
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun movieSuccess(movie: MoviesDetailResponse) {
        (activity as BaseActivity).supportActionBar?.title = movie.title
        viewDetailMovie.visibility = View.VISIBLE
        progressbarDetailMovie.visibility = View.GONE
        fab.visibility = View.VISIBLE
        listProductionRecycler.adapter =
            MyMoviesDetailRecyclerViewAdapter(movie.productionCompanies!!)
        Glide.with(this)
            .load(Constants.BASE_URL_IMAGE + movie.posterPath)
            .into(movieDetailImg)
        movieDetailTitle.text = movie.title
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(movie.releaseDate)
        var currentDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(date)
        movieDetailYear.text = currentDate
        txtOverview.text = movie.overview

        txtGenres.text = movie.genres!!.joinToString(separator = ", ") { "${it.name}" }
        txtCountry.text =
            movie.productionCountries!!.joinToString(separator = ", ") { "${it.name}" }
    }


}
