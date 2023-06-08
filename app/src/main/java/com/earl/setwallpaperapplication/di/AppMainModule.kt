package com.earl.setwallpaperapplication.di

import com.earl.setwallpaperapplication.data.RepositoryImpl
import com.earl.setwallpaperapplication.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class AppMainModule {

    @Provides
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }
}