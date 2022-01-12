package com.example.top100cryptocurrency.di

import com.example.top100cryptocurrency.chart.LatestChart
import com.example.top100cryptocurrency.formatters.YearValueFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {
    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}

