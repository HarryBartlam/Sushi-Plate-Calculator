package com.simplyapp.plate.calculator.ui

import com.simplyapp.plate.calculator.ui.calculator.CalculatorComponent
import com.simplyapp.plate.calculator.ui.settings.SettingsComponent
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = arrayOf(AndroidInjectionModule::class
        , CalculatorComponent.CalculatorModule::class
        , SettingsComponent.SettingsModule::class))
class ActivityModule