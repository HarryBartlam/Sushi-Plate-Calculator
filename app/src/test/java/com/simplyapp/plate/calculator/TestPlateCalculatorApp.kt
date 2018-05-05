package com.simplyapp.plate.calculator

import com.simplyapp.plate.calculator.testing.TestTree
import timber.log.Timber

class TestPlateCalculatorApp : PlateCalculatorApp() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TestTree())
    }

}
