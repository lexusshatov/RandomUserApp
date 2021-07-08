package com.example.randomuserapp.view.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.randomuserapp.R
import com.example.randomuserapp.base.BaseFragment
import com.example.randomuserapp.databinding.FragmentUserListBinding
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.view.UserHostActivity
import com.example.randomuserapp.view.details.UserDetailFragment
import com.example.randomuserapp.viewmodel.UserListViewModel

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>() {

    private val TAG = this.javaClass.simpleName
    private lateinit var adapter: UserRecyclerViewAdapter

    override val viewModelProvider: () -> UserListViewModel =
        {
            UserListViewModel()
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserListBinding =
        { inflater, container ->
            FragmentUserListBinding.inflate(inflater, container, false)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDetailFragmentContainer: View? = view.findViewById(R.id.user_detail_nav_container)
        val onClickListener = View.OnClickListener { userView ->
            val user = userView.tag as User
            val bundle = Bundle()
            bundle.putLong(
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
        }

        adapter = UserRecyclerViewAdapter(
            emptyList<User>().toMutableList(),
            onClickListener
        )
        binding.userRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        viewModel.loadData()
    }

}