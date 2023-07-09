package com.project.architecture.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.project.architecture.R
import com.project.architecture.data.remote.ApiService
import com.project.architecture.data.repository.MainRepository
import com.project.architecture.ui.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext app: Context) : MyApplication{
        return app as MyApplication
    }

    @Provides
    @Singleton
    fun provideSharedPref(
        context: Application
    ): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.sharedPrefLocal), 0)
    }

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService) : MainRepository{
        return MainRepository(apiService)
    }
}