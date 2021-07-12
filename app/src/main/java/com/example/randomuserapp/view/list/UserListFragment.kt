package com.example.randomuserapp.view.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.randomuserapp.R
import com.example.randomuserapp.base.BaseFragment
import com.example.randomuserapp.databinding.FragmentUserListBinding
import com.example.randomuserapp.model.base.DI
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.view.details.UserDetailFragment
import com.example.randomuserapp.view.list.adapter.UserAdapter
import com.example.randomuserapp.viewmodel.UserListViewModel

private val TAG = UserListFragment::class.java.simpleName

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>() {

    override val viewModelProvider: () -> UserListViewModel =
        {
            UserListViewModel(DI.repository)
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserListBinding =
        { inflater, container ->
            FragmentUserListBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDetailFragmentContainer: View? = view.findViewById(R.id.user_detail_container)
        val adapter = UserAdapter(
            onClickListener = { userView ->
                val user = userView.tag as User
                val bundle = Bundle()
                bundle.putString(
                    UserDetailFragment.ARG_USER_ID,
                    user.id
                )
                Log.d(TAG, "User id: ${user.id}")
                if (userDetailFragmentContainer != null) {
                    userDetailFragmentContainer.findNavController()
                        .navigate(R.id.fragment_user_detail, bundle)
                } else {
                    userView.findNavController().navigate(R.id.show_user_detail, bundle)
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