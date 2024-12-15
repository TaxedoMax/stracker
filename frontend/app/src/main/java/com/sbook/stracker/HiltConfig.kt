package com.sbook.stracker

import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.TeamRepository
import com.sbook.stracker.repository.UserRepository
import com.sbook.stracker.repository.impl.TaskRepositoryImpl
import com.sbook.stracker.repository.impl.TeamRepositoryImpl
import com.sbook.stracker.repository.impl.UserRepositoryImpl
import com.sbook.stracker.repository.mock.InMemoryTaskRepository
import com.sbook.stracker.repository.mock.InMemoryTeamRepository
import com.sbook.stracker.repository.mock.InMemoryUserRepository
import com.sbook.stracker.viewmodel.TaskViewModel
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
    fun taskEditViewModelFactory(): TaskViewModel.Factory
}
@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @Singleton
    fun provideInMemoryUserRepository(inMemoryTeamRepository: InMemoryTeamRepository): InMemoryUserRepository {
        return InMemoryUserRepository(inMemoryTeamRepository)
    }
    @Provides
    @Singleton
    fun provideUserRepositoryImpl(userRepositoryImpl: UserRepositoryImpl): UserRepositoryImpl{
        return UserRepositoryImpl()
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
    @Provides
    @Singleton
    fun provideTaskRepositoryImpl(taskRepositoryImpl: TaskRepositoryImpl): TaskRepositoryImpl{
        return TaskRepositoryImpl()
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
    @Provides
    @Singleton
    fun provideTeamRepositoryImpl(teamRepositoryImpl: TeamRepositoryImpl): TeamRepositoryImpl{
        return TeamRepositoryImpl()
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
