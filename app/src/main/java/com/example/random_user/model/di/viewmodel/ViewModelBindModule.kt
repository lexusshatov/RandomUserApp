package com.example.random_user.model.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.random_user.viewmodel.UserListViewModel
import dagger.*
import dagger.multibindings.IntoMap

@Module(subcomponents = [UserDetailsViewModelComponent::class])
interface ViewModelBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    fun bindUserListViewModel(userListViewModel: UserListViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}



