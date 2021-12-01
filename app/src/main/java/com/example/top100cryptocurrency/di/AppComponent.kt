package com.example.top100cryptocurrency.di

import com.example.top100cryptocurrency.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MVPModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}