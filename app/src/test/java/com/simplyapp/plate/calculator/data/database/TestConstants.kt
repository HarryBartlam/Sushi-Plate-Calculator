package com.simplyapp.plate.calculator.data.database

import com.simplyapp.plate.calculator.BuildConfig
import com.simplyapp.plate.calculator.GradleRobolectricTestRunner
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_BLACK
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_BLUE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GOLD
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GRAY
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GREEN
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_ORANGE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PINK
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PURPLE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_SUMO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(GradleRobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(BuildConfig.TEST_VERSION), packageName = "com.simplyapp.plate.calculator")
class TestConstants {

    val plateValueGreen = 2.10f
    val plateValueBlue = 2.80f
    val plateValuePurple = 3.70f
    val plateValueOrange = 4.20f
    val plateValuePink = 4.60f
    val plateValueGray = 5.20f
    val plateValueGold = 6.00f
    val plateValueSumo = 9.00f
    val plateValueBlack = 2.20f

    @Before
    fun setup() {

    }

    @Test
    fun testPlateValueConstants() {
        Assert.assertEquals("Green plate value ", plateValueGreen, PLATE_VALUE_GREEN)
        Assert.assertEquals("Blue plate value", plateValueBlue, PLATE_VALUE_BLUE)
        Assert.assertEquals("Purple plate value", plateValuePurple, PLATE_VALUE_PURPLE)
        Assert.assertEquals("Orange plate value", plateValueOrange, PLATE_VALUE_ORANGE)
        Assert.assertEquals("Pink plate value", plateValuePink, PLATE_VALUE_PINK)
        Assert.assertEquals("Gray plate value", plateValueGray, PLATE_VALUE_GRAY)
        Assert.assertEquals("Gold plate value", plateValueGold, PLATE_VALUE_GOLD)
        Assert.assertEquals("Sumo plate value", plateValueSumo, PLATE_VALUE_SUMO)
        Assert.assertEquals("Black plate value", plateValueBlack, PLATE_VALUE_BLACK)
    }

    @After
    fun close() {

    }

}