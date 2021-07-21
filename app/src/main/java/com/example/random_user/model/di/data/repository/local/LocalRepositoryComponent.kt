package com.example.random_user.model.di.data.repository.local

import com.example.random_user.model.di.data.DataComponent
import com.example.random_user.model.di.data.repository.RepositoryScope
import com.example.random_user.model.local.User
import com.example.random_user.model.repository.DataCache
import dagger.Component

@RepositoryScope
@Component(
    dependencies = [DataComponent::class],
    modules = [
        LocalRepoBindModule::class
    ]
)
interface LocalRepositoryComponent {

    fun getRepository(): DataCache<User, String>
}