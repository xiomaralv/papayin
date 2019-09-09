package com.fandango.papayin.presentation.modules.list.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fandango.papayin.Constants
import com.fandango.papayin.R
import com.fandango.papayin.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.item_loading.view.*


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMoviesRecyclerViewAdapter(
    private val listMovies: MutableList<Movie>,
    private val listener: View.OnClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {


    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            VIEW_TYPE_NORMAL -> return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            )
            VIEW_TYPE_LOADING -> return ProgressHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
            else -> return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position === listMovies.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int = listMovies.size


    fun addItems(postItems: List<Movie>) {
        listMovies.addAll(postItems)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        listMovies.add(Movie())
        notifyItemInserted(listMovies.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = listMovies.size - 1
        val item = getItem(position)
        if (item != null) {
            listMovies.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        listMovies.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Movie? {
        return listMovies.get(position)
    }


    inner class ViewHolder(val mView: View) : BaseViewHolder(mView) {

        val mImgMovie: ImageView = mView.movieItemImg
        val mTitleMovie: TextView = mView.movieItemTitle
        val mYearMovie: TextView = mView.movieItemYear


        override fun clear() {

        }

        override fun onBind(position: Int) {
            super.onBind(position)
            val movie: Movie = listMovies[position]

            mTitleMovie.text = movie.title
            mYearMovie.text = movie.releaseDate!!.split("-")[0]
            Glide.with(mView)
                .load(Constants.BASE_URL_IMAGE + movie.posterPath)
                .into(mImgMovie)
            with(mView) {
                tag = movie
                setOnClickListener(listener)
            }
        }
    }

    inner class ProgressHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {

        val progressLoading: ProgressBar = itemView.progressLoading

        override fun clear() {}
    }
}
