package com.example.random_user.model.di.viewmodel

import com.example.random_user.viewmodel.UserDetailsViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [UserDetailsViewModelModule::class])
interface UserDetailsViewModelComponent {

    val viewModel: UserDetailsViewModel

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance userId: String): UserDetailsViewModelComponent
    }
}