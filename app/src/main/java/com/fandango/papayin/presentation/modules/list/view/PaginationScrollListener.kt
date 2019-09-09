package com.fandango.papayin.presentation.modules.list.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {

    private val PAGE_SIZE = 20

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}