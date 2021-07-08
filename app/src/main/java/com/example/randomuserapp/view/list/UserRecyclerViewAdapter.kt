package com.example.randomuserapp.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.databinding.UserListContentBinding
import com.example.randomuserapp.model.local.User

class UserRecyclerViewAdapter(
    private val values: MutableList<User>,
    private val onClickListener: View.OnClickListener,
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = values[position]
        holder.contentView.text = "${user.firstName} ${user.lastName}"

        with(holder.itemView) {
            tag = user
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: UserListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content
    }

    fun submitList(users: List<User>){
        values.clear()
        values.addAll(users)
        notifyDataSetChanged()
    }

}