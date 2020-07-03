package com.telepuz.android.di

import com.telepuz.android.network.TelepuzWebSocketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideTelepuzWebSocketService(): TelepuzWebSocketService = TelepuzWebSocketService()
}