package com.example.hilttutorialbydanny.data.di

import android.content.Context
import com.example.hilttutorialbydanny.data.UserPreferences
import com.example.hilttutorialbydanny.data.network.AuthApi
import com.example.hilttutorialbydanny.data.network.RemoteDataSource
import com.example.hilttutorialbydanny.data.network.UserApi
import com.example.hilttutorialbydanny.data.repo.AuthRepository
import com.example.hilttutorialbydanny.data.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java, context)
    }


    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authApi: AuthApi, userPreferences: UserPreferences): AuthRepository {
        return AuthRepository(authApi, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java, context)
    }


    @Singleton
    @Provides
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository(userApi)
    }
}