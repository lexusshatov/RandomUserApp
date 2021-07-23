package com.example.random_user.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.random_user.R
import com.example.random_user.databinding.ActivityUserDetailBinding
import com.example.random_user.view.list.UserListFragment

class UserHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, UserListFragment())
            .commit()
    }
}