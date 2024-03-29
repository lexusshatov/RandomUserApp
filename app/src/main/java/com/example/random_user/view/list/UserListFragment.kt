package com.example.random_user.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.random_user.R
import com.example.random_user.base.BaseFragment
import com.example.random_user.databinding.FragmentUserListBinding
import com.example.random_user.model.base.DI
import com.example.random_user.view.details.UserDetailFragment
import com.example.random_user.view.list.adapter.UserAdapter
import com.example.random_user.viewmodel.UserListViewModel

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>() {

    override val viewModelProvider: () -> UserListViewModel =
        {
            UserListViewModel(DI.getInstance().repository)
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserListBinding =
        { inflater, container ->
            FragmentUserListBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter(
            onClickListener = { user ->
                val bundle = Bundle()
                bundle.putString(
                    UserDetailFragment.ARG_USER_ID,
                    user.id
                )
                view.findNavController().navigate(R.id.show_user_detail, bundle)
            },
            onPagination = {
                viewModel.loadData()
            }
        )
        binding.userRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                viewModel.loadData()
            }
            adapter.submitList(it.toMutableList())
        })
    }
}