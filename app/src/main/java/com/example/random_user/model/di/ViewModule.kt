package com.example.random_user.model.di

import com.example.random_user.model.di.viewmodel.ViewModelBindModule
import com.example.random_user.view.UserHostActivity
import com.example.random_user.view.details.UserDetailsFragment
import com.example.random_user.view.list.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelBindModule::class])
interface ViewModule {

    @ContributesAndroidInjector
    fun contributeProductUserHostActivity(): UserHostActivity

    @ContributesAndroidInjector
    fun contributeProductUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    fun contributeProductUserDetailFragment(): UserDetailsFragment
}