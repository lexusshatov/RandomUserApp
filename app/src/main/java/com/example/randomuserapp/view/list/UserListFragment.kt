package com.example.randomuserapp.view.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.R
import com.example.randomuserapp.base.BaseFragment
import com.example.randomuserapp.databinding.FragmentUserListBinding
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.view.details.UserDetailFragment
import com.example.randomuserapp.view.list.adapter.PaginationListener
import com.example.randomuserapp.view.list.adapter.UserAdapter
import com.example.randomuserapp.view.list.adapter.UserAdapterTouchHelper
import com.example.randomuserapp.viewmodel.UserListViewModel

private val TAG = UserListFragment::class.java.simpleName

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>() {
    override val viewModelProvider: () -> UserListViewModel =
        {
            UserListViewModel()
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserListBinding =
        { inflater, container ->
            FragmentUserListBinding.inflate(inflater, container, false)
        }
    private lateinit var adapterTouchHelper: UserAdapterTouchHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDetailFragmentContainer: View? = view.findViewById(R.id.user_detail_container)

        adapterTouchHelper = UserAdapterTouchHelper(
            UserAdapter(
                values = emptyList<User>().toMutableList(),
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
                onSwiped = { userView ->
                    val user = userView.tag as User
                    viewModel.deleteUser(user)
                }
            )
        )
        val callback: ItemTouchHelper.Callback = adapterTouchHelper
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.userRecyclerView)
        binding.userRecyclerView.adapter = adapterTouchHelper.adapter
        binding.userRecyclerView.addOnScrollListener(
            PaginationListener {
                viewModel.loadRemote()
            }
        )

        viewModel.data.observe(viewLifecycleOwner, {
            adapterTouchHelper.adapter.submitList(it)
        })
        viewModel.loadData()
    }
}