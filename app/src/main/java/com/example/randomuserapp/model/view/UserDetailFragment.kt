package com.example.randomuserapp.model.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.randomuserapp.UserApp
import com.example.randomuserapp.databinding.FragmentUserDetailBinding
import com.example.randomuserapp.model.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

//TODO viewModel
class UserDetailFragment : Fragment() {

    private var user: User? = null

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_USER_ID)) {
                user = runBlocking (Dispatchers.IO) {
                    UserApp.database.userDao().getUserById(it.getLong(ARG_USER_ID))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user?.let {

            binding.toolbarLayout?.title = "${it.firstName} ${it.lastName}"
            val userDetailsList = listOf(
                it.gender,
                it.country,
                it.city,
                "${it.street}, ${it.streetNumber}",
                it.email
            )

            userDetailsList.filterNotNull().forEach {
                val textView = TextView(context)
                textView.apply {
                    text = it
                    textSize = 20F
                }
                binding?.userDetails?.addView(textView)
            }
        }
    }

    companion object {
        const val ARG_USER_ID = "user_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}