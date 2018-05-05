package com.simplyapp.plate.calculator

import com.simplyapp.plate.calculator.data.DataModule
import com.simplyapp.plate.calculator.ui.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class, ActivityModule::class))
interface AppComponent : BaseAppComponent
