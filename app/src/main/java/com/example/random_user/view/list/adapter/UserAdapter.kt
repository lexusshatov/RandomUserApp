package com.example.random_user.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.random_user.databinding.UserListContainerBinding
import com.example.random_user.model.local.User

class UserAdapter(
    private val onClickListener: (View) -> Unit,
    private val onPagination: () -> Unit
) : ListAdapter<User, UserAdapter.ViewHolder>(DiffCallback) {

    private object DiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserListContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: UserListContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClickListener: (View) -> Unit) {
            val title = "${user.firstName} ${user.lastName}"
            binding.content.text = title
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

