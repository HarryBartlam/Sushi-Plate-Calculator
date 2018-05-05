package com.simplyapp.plate.calculator.data.database

import android.arch.persistence.room.Room
import com.simplyapp.plate.calculator.BuildConfig
import com.simplyapp.plate.calculator.GradleRobolectricTestRunner
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.data.model.PlateConstants
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(GradleRobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.JVM)
@Config(constants = BuildConfig::class, sdk = intArrayOf(BuildConfig.TEST_VERSION), packageName = "com.simplyapp.plate.calculator")
class TestDatabaseAndModels {
    lateinit var appDatabase: AppDatabase
    lateinit var plateDao: PlateDao

    @Before
    fun setup() {
        val application = RuntimeEnvironment.application

        appDatabase = Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java).allowMainThreadQueries().build()
        plateDao = appDatabase.plateDao()

    }

    @Test
    fun testPlateSaving() {
        val green = Plate(PlateConstants.PLATE_ID_GREEN, "Green Plate", 9)
        val blue = Plate(PlateConstants.PLATE_ID_BLUE, "Blue Plate", 0)
        val purple = Plate(PlateConstants.PLATE_ID_PURPLE, "Purple Plate", 0)
        val orange = Plate(PlateConstants.PLATE_ID_ORANGE, "Orange Plate", 4)
        val pink = Plate(PlateConstants.PLATE_ID_PINK, "Pink Plate", 0)
        val gray = Plate(PlateConstants.PLATE_ID_GRAY, "Gray Plate", 6)
        val yellow = Plate(PlateConstants.PLATE_ID_GOLD, "Gold Plate", 0)
        val sumo = Plate(PlateConstants.PLATE_ID_SUMO, "Sumo Plate", 0)
        val black = Plate(PlateConstants.PLATE_ID_BLACK, "Black Plate", 999)

        val platesList = listOf(green, blue, purple, orange, pink, gray, yellow, sumo, black)

        plateDao.insertPlate(platesList)

        val databaseList = plateDao.getAllPlates().blockingGet()

        Assert.assertEquals("Testing Saving database", platesList, databaseList)
    }

    @After
    fun close() {
        appDatabase.close()
    }

}