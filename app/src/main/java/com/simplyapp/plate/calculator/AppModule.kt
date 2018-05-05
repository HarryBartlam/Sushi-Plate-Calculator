package com.simplyapp.plate.calculator

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun application(): Application {
        return application
    }
}
