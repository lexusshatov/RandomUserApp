package com.example.random_user.model.di.repository.local

import com.example.random_user.model.di.repository.RepositoryScope
import com.example.random_user.model.di.repository.data.DataComponent
import com.example.random_user.model.repository.local.User
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