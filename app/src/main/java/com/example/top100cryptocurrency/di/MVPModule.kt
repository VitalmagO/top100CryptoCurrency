package com.example.top100cryptocurrency.di

import com.example.top100cryptocurrency.mvp.presenter.CryptoCurrencyPresenter
import com.example.top100cryptocurrency.mvp.presenter.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MVPModule {
    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CryptoCurrencyPresenter = CryptoCurrencyPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()
}