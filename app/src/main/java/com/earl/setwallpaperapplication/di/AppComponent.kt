package com.earl.setwallpaperapplication.di

import androidx.lifecycle.ViewModel
import dagger.Component

@[AppScope Component(
    modules = [
        AppMainModule::class
    ]
)]
interface AppComponent {



    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }
}

internal class AppComponentViewModel : ViewModel() {

    val appComponent = DaggerAppComponent.builder().build()
}