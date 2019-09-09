package com.fandango.papayin.presentation.modules.list.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fandango.papayin.R
import com.fandango.papayin.data.model.Movie
import com.fandango.papayin.data.source.response.MoviesResponse
import com.fandango.papayin.presentation.modules.detail.view.DetailMovieFragment
import com.fandango.papayin.presentation.modules.list.presenter.IListMoviesPresenter
import com.fandango.papayin.presentation.modules.list.presenter.ListMoviesPresenterImpl
import kotlinx.android.synthetic.main.fragment_movies_list.*
import java.util.ArrayList

class MoviesFragment : Fragment(), IMoviesFragment, SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener {
    override fun onClick(v: View?) {
        val item = v?.tag as Movie
        activity!!.supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, DetailMovieFragment.newInstance(item.id), "movieList")
            .addToBackStack(null)
            .commit()
    }

    private val PAGE_START = 1
    private var page = PAGE_START
    private var isLastPage = false
    private var totalPage = 20
    private var numberReg: Int = 1
    private val total: Int = 20
    private var isLoading = false
    private var itemCount = 0
    private var moviesListPresenter: IListMoviesPresenter = ListMoviesPresenterImpl(this)
    private var myCustomAdapter: MyMoviesRecyclerViewAdapter
    private val listMovie: MutableList<Movie> = ArrayList()

    init {
        myCustomAdapter = MyMoviesRecyclerViewAdapter(listMovie, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(PAGE_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        val screenOrientation =
            (context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation
        var layoutManager = if (screenOrientation == Surface.ROTATION_0) {
            GridLayoutManager(context, 2)
        } else {
            GridLayoutManager(context, 3)
        }
        listMovies.layoutManager = layoutManager
        listMovies.setHasFixedSize(true)
        preparedListItem()
        listMovies.adapter = myCustomAdapter
        listMovies.addOnScrollListener(object :
            PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                page++
                preparedListItem()
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    companion object {
        const val PAGE_NUMBER = "page-number"

        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE_NUMBER, page)
                }
            }
    }

    private fun preparedListItem() {
        if (total > numberReg) {
            moviesListPresenter.getMovies(page)
        } else {
            myCustomAdapter.removeLoading()
        }
    }

    override fun onRefresh() {
        itemCount = 0
        page = PAGE_START
        isLastPage = false
        myCustomAdapter.clear()
        preparedListItem()
    }

    override fun moviesSuccess(movies: MoviesResponse) {
        linearMovies!!.visibility = View.VISIBLE
        progressbarListMovies!!.visibility = View.GONE
        val listOfMovies: MutableList<Movie> = movies.movies!!
        numberReg = movies.page
        totalPage = movies.totalPages
        if (page != PAGE_START) myCustomAdapter.removeLoading()
        myCustomAdapter.addItems(listOfMovies)
        swipeRefresh.isRefreshing = false
        if (page < totalPage) myCustomAdapter.addLoading()
        else isLastPage = true
        isLoading = false
    }

    override fun moviesError(exception: Exception) {
        progressbarListMovies.visibility = View.GONE
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }
}
