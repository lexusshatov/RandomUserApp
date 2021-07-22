package com.example.random_user.model.di.viewmodel

import com.example.random_user.model.repository.DataRepository
import com.example.random_user.viewmodel.UserDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class UserDetailsViewModelModule {

    @Provides
    fun provideUserDetailsViewModel(
        repository: DataRepository,
        userId: String
    ) = UserDetailsViewModel(repository, userId)
}