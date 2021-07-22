package com.example.random_user.model.di

import com.example.random_user.view.UserHostActivity
import com.example.random_user.view.details.UserDetailFragment
import com.example.random_user.view.list.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ViewModule {

    @ContributesAndroidInjector
    fun contributeProductUserHostActivity(): UserHostActivity

    @ContributesAndroidInjector
    fun contributeProductUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    fun contributeProductUserDetailFragment(): UserDetailFragment
}