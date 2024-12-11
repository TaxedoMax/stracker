package com.sbook.stracker

import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.TeamRepository
import com.sbook.stracker.repository.UserRepository
import com.sbook.stracker.repository.mock.InMemoryTaskRepository
import com.sbook.stracker.repository.mock.InMemoryTeamRepository
import com.sbook.stracker.repository.mock.InMemoryUserRepository
import com.sbook.stracker.viewmodel.TeamEditViewModel
import com.sbook.stracker.viewmodel.TeamTasksViewModel
import com.sbook.stracker.viewmodel.TeamsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider{
    fun teamViewModelFactory(): TeamsViewModel.Factory
    fun teamEditViewModelFactory(): TeamEditViewModel.Factory
    fun teamTasksViewModelFactory(): TeamTasksViewModel.Factory
}
@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @Singleton
    fun provideInMemoryUserRepository(inMemoryTeamRepository: InMemoryTeamRepository): InMemoryUserRepository {
        return InMemoryUserRepository(inMemoryTeamRepository)
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
    @Provides
    @Singleton
    fun provideInMemoryTeamRepository(): InMemoryTeamRepository{
        return InMemoryTeamRepository()
    }
}
@InstallIn(SingletonComponent::class)
@Module
abstract class BindingModule{
    @Binds
    abstract fun bindUserRepository(inMemoryUserRepository: InMemoryUserRepository): UserRepository
    @Binds
    abstract fun bindTaskRepository(inMemoryTaskRepository: InMemoryTaskRepository): TaskRepository
    @Binds
    abstract fun bindTeamRepository(inMemoryTeamRepository: InMemoryTeamRepository): TeamRepository
}
