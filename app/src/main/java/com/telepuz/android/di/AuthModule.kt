package com.telepuz.android.di

import com.telepuz.android.model.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepository()
}