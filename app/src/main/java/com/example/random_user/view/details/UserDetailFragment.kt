package com.example.random_user.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.random_user.R
import com.example.random_user.base.BaseFragment
import com.example.random_user.databinding.FragmentUserDetailBinding
import com.example.random_user.model.base.DI
import com.example.random_user.model.local.User
import com.example.random_user.utils.emptyString
import com.example.random_user.viewmodel.UserDetailsViewModel

private val TAG = UserDetailFragment::class.java.simpleName

class UserDetailFragment : BaseFragment<UserDetailsViewModel, FragmentUserDetailBinding>() {

    override val viewModelProvider: () -> UserDetailsViewModel =
        {
            UserDetailsViewModel(DI.repository, userId)
        }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentUserDetailBinding =
        { inflater, container ->
            FragmentUserDetailBinding.inflate(inflater, container, false)
        }
    private val userId: String by lazy {
        val id = arguments?.getString(ARG_USER_ID, emptyString()) ?: emptyString()
        Log.d(TAG, "User id: $id")
        id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, {
            showUser(it)
        })
    }

    private fun showUser(user: User) {
        binding.apply {
            userAvatar.let {
                val defaultDrawable = AppCompatResources.getDrawable(
                    requireActivity(),
                    if (user.gender == "male")
                        R.drawable.avatar_default_male
                    else
                        R.drawable.avatar_default_female
                )
                activity?.let {
                    Glide.with(it)
                        .load(user.pictureUrl)
                        .placeholder(defaultDrawable)
                        .into(userAvatar)
                }
            }
            toolbarLayout.title = getString(
                R.string.user_details_title,
                user.firstName, user.lastName
            )
            userGender.text = getString(
                R.string.user_details_gender_age,
                user.gender, user.age
            )
            userLocation.text = getString(
                R.string.user_details_location,
                user.country, user.city, user.street, user.streetNumber
            )
            userPhone.text = user.phone
            userEmail.text = user.email
        }
    }

    companion object {
        const val ARG_USER_ID = "user_id"
    }
}