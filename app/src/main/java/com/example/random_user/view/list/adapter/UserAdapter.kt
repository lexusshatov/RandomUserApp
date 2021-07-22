package com.example.random_user.view.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.random_user.databinding.UserListContainerBinding
import com.example.random_user.model.repository.local.User

private val TAG: String = UserAdapter::class.java.simpleName

class UserAdapter(
    private val onClickListener: (User) -> Unit,
    private val onPagination: () -> Unit
) : ListAdapter<User, UserAdapter.ViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserListContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (position == currentList.lastIndex - usersLeftBeforeScroll) {
            onPagination()
        }
        holder.bind(user, onClickListener)
    }

    override fun getItemCount() = currentList.size

    class ViewHolder(private val binding: UserListContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClickListener: (User) -> Unit) {
            val title = "${user.firstName} ${user.lastName}"
            binding.content.text = title
            with(binding.root) {
                setOnClickListener {
                    onClickListener(user)
                    Log.d(TAG, "User ID: ${user.id}")
                }
            }
        }
    }

    companion object {
        private const val usersLeftBeforeScroll = 3
    }
}

