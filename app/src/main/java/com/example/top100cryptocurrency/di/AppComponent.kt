package com.example.top100cryptocurrency.di

import com.example.top100cryptocurrency.activities.MainActivity
import com.example.top100cryptocurrency.fragments.CryptoCurrencyListFragment
import com.example.top100cryptocurrency.mvp.presenter.CryptoCurrencyPresenter
import com.example.top100cryptocurrency.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MVPModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CryptoCurrencyPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CryptoCurrencyListFragment)
}