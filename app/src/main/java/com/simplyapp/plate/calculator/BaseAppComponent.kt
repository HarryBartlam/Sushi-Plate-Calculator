package com.simplyapp.plate.calculator

import com.simplyapp.plate.calculator.ui.views.SettingPrefView

interface BaseAppComponent {
    fun inject(plateCalculatorApp: PlateCalculatorApp)
    fun inject(plateCalculatorApp: SettingPrefView)
}
