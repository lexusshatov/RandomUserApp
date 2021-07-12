package com.example.randomuserapp.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.databinding.UserListContentBinding
import com.example.randomuserapp.model.local.User

class UserAdapter(
    private val onClickListener: (View) -> Unit,
    private val onPagination: () -> Unit
) : ListAdapter<User, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    private object DIFF_CALLBACK: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (position == currentList.lastIndex - 3) {
            onPagination()
        }
        holder.bind(user, onClickListener)
    }

    override fun getItemCount() = currentList.size

    class ViewHolder(private val binding: UserListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClickListener: (View) -> Unit) {
            binding.content.text = "${user.firstName} ${user.lastName}"
            with(itemView) {
                tag = user
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun submitList(list: MutableList<User>?) {
        super.submitList(list)
    }
}

