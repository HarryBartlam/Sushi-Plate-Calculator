package com.simplyapp.plate.calculator.data.preferences

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {
    @Provides
    @Singleton
    internal fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
