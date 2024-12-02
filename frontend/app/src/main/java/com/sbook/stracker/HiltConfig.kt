package com.sbook.stracker

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.UserRepository
import com.sbook.stracker.repository.mock.InMemoryTaskRepository
import com.sbook.stracker.repository.mock.InMemoryUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @Singleton
    fun provideInMemoryUserRepository(): InMemoryUserRepository {
        return InMemoryUserRepository()
    }
}

@InstallIn(SingletonComponent::class)
@Module
class TaskModule{
    @Provides
    @Singleton
    fun provideInMemoryTaskRepository(): InMemoryTaskRepository{
        return  InMemoryTaskRepository()
    }
}
@InstallIn(SingletonComponent::class)
@Module
class TeamModule{

}
@InstallIn(SingletonComponent::class)
@Module
abstract class BindingModule{
    @Binds
    abstract fun bindUserRepository(inMemoryUserRepository: InMemoryUserRepository) : UserRepository
    @Binds
    abstract fun bindTaskRepository(inMemoryTaskRepository: InMemoryTaskRepository): TaskRepository
}
