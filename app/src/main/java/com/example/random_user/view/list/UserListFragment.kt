package com.example.random_user.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.random_user.R
import com.example.random_user.base.BaseFragment
import com.example.random_user.databinding.FragmentUserListBinding
import com.example.random_user.view.details.UserDetailFragment
import com.example.random_user.view.list.adapter.UserAdapter
import com.example.random_user.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>() {
    override val viewModel by viewModel<UserListViewModel>()
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserListBinding =
        { inflater, container ->
            FragmentUserListBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter(
            onClickListener = { user ->
                activity?.apply {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            UserDetailFragment.newInstance(user.id)
                        ).addToBackStack(null)
                        .commit()
                }
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