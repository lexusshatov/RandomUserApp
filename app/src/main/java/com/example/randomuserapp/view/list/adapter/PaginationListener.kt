package com.example.randomuserapp.view.list.adapter

import androidx.recyclerview.widget.RecyclerView

class PaginationListener(private val onScroll: () -> Unit): RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING
            && !recyclerView.canScrollVertically(1)
        )
            onScroll()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScroll()
    }
}