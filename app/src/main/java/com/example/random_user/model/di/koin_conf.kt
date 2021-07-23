package com.example.random_user.model.di

import androidx.room.Room
import com.example.random_user.model.repository.*
import com.example.random_user.model.repository.local.User
import com.example.random_user.model.repository.local.UserDatabase
import com.example.random_user.model.repository.remote.Result
import com.example.random_user.model.repository.remote.UserApi
import com.example.random_user.viewmodel.UserDetailsViewModel
import com.example.random_user.viewmodel.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val databaseName = "User_Database"
const val baseUrl = "https://randomuser.me/"

val dataModule = module {
    single<UserApi> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            databaseName
        )
            .fallbackToDestructiveMigration()
            .build().userDao()
    }
}

val repositoryModule = module {
    factory<DataFetcher<List<Result>>> { ApiRepository(get()) }
    factory<DataCache<User, String>> { LocalRepository(get()) }
}

val decoratorModule = module {
    single<DataRepository> { RepositoryDecorator(get(), get()) }
}

val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
    viewModel { (userId: String) -> UserDetailsViewModel(get(), userId) }
}