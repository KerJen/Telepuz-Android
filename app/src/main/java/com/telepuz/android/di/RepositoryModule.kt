package com.telepuz.android.di

import com.telepuz.android.model.repository.AuthRepository
import com.telepuz.android.model.repository.MainRepository
import com.telepuz.android.network.TelepuzWebSocketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(service: TelepuzWebSocketService): AuthRepository =
        AuthRepository(service)

    @Provides
    @Singleton
    fun provideMainRepository(service: TelepuzWebSocketService): MainRepository =
        MainRepository(service)
}