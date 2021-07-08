package com.example.randomuserapp.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.randomuserapp.R
import com.example.randomuserapp.base.BaseFragment
import com.example.randomuserapp.databinding.FragmentUserDetailBinding
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.viewmodel.UserDetailsViewModel


class UserDetailFragment : BaseFragment<UserDetailsViewModel, FragmentUserDetailBinding>() {

    private val TAG = this.javaClass.simpleName

    override val viewModelProvider: () -> UserDetailsViewModel =
        {
            UserDetailsViewModel(userId)
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserDetailBinding =
        { inflater, container ->
            FragmentUserDetailBinding.inflate(inflater, container, false)
        }
    private val userId: Long by lazy {
        val id = arguments?.getLong(ARG_USER_ID, -1) ?: -1
        Log.d(TAG, "User id: $id")
        id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, {
            showUser(it)
        })
        viewModel.loadData()
    }

    private fun showUser(user: User){

        if (user.pictureUrl != null){
            activity?.let {
                binding.userAvatar?.let { view ->
                    val defaultDrawable = AppCompatResources.getDrawable(
                        requireActivity(),
                        if (user.gender == "male")
                            R.drawable.avatar_default_male
                        else
                            R.drawable.avatar_default_female
                    )
                    Glide.with(it)
                        .load(user.pictureUrl)
                        .placeholder(defaultDrawable)
                        .into(view)
                }
            }
        }

        binding.apply {
            toolbarLayout?.title = "${user.firstName} ${user.lastName}"
            userGender.text = user.gender
            userLocation?.text = "${user.country}, ${user.city}, ${user.street}, ${user.streetNumber}"
            userPhone?.text = "${user.phone}"
            userEmail?.text = "${user.email}"
        }
    }

    companion object {
        const val ARG_USER_ID = "user_id"
    }
}