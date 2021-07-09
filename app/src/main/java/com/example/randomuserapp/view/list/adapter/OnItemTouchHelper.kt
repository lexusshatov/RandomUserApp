package com.example.randomuserapp.view.list.adapter

import androidx.recyclerview.widget.RecyclerView

interface OnItemTouchHelper {

    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
}