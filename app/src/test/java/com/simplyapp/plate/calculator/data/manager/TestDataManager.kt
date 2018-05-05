package com.simplyapp.plate.calculator.data.manager

import android.arch.persistence.room.Room
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.simplyapp.plate.calculator.BuildConfig
import com.simplyapp.plate.calculator.GradleRobolectricTestRunner
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.database.AppDatabase
import com.simplyapp.plate.calculator.data.database.PlateDao
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.data.model.PlateConstants
import com.simplyapp.plate.calculator.data.model.PlateType
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RuntimeEnvironment
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.annotation.Config

@RunWith(GradleRobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.JVM)
@Config(constants = BuildConfig::class, sdk = intArrayOf(BuildConfig.TEST_VERSION), packageName = "com.simplyapp.plate.calculator")
class TestDataManager {
    lateinit var appDatabase: AppDatabase

    lateinit var plateDao: PlateDao

    lateinit var sharedPreferences: SharedPreferences

    lateinit var dataManager: DataManager

    lateinit var platesList: List<Plate>

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java).allowMainThreadQueries().build()
        plateDao = appDatabase.plateDao()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
        dataManager = DataManagerImpl(plateDao, sharedPreferences, RuntimeEnvironment.application)
        initDatabaseAndPrefs()
    }

    fun initDatabaseAndPrefs() {
        //given
        val green = Plate(0, "Green Plate", 0, PlateType.GREEN_PLATE)
        val blue = Plate(1, "Blue Plate", 0, PlateType.BLUE_PLATE)
        val purple = Plate(2, "Purple Plate", 0, PlateType.PURPLE_PLATE)
        val orange = Plate(3, "Orange Plate", 0, PlateType.ORANGE_PLATE)
        val pink = Plate(4, "Pink Plate", 0, PlateType.PINK_PLATE)
        val gray = Plate(5, "Gray Plate", 0, PlateType.GRAY_PLATE)
        val yellow = Plate(6, "Gold Plate", 0, PlateType.GOLD_PLATE)
        val sumo = Plate(7, "Sumo Plate", 0, PlateType.SUMO_PLATE)
        val black = Plate(8, "Black Plate", 0, PlateType.BLACK_PLATE)

        platesList = listOf(green, blue, purple, orange, pink, gray, yellow, sumo, black)

        plateDao.insertPlate(platesList)

        sharedPreferences.edit()
                .putFloat(application.getString(R.string.pref_green_plate), PlateConstants.PLATE_VALUE_GREEN)
                .putFloat(application.getString(R.string.pref_blue_plate), PlateConstants.PLATE_VALUE_BLUE)
                .putFloat(application.getString(R.string.pref_purple_plate), PlateConstants.PLATE_VALUE_PURPLE)
                .putFloat(application.getString(R.string.pref_orange_plate), PlateConstants.PLATE_VALUE_ORANGE)
                .putFloat(application.getString(R.string.pref_pink_plate), PlateConstants.PLATE_VALUE_PINK)
                .putFloat(application.getString(R.string.pref_gray_plate), PlateConstants.PLATE_VALUE_GRAY)
                .putFloat(application.getString(R.string.pref_gold_plate), PlateConstants.PLATE_VALUE_GOLD)
                .putFloat(application.getString(R.string.pref_sumo_plate), PlateConstants.PLATE_VALUE_SUMO)
                .putFloat(application.getString(R.string.pref_black_plate), PlateConstants.PLATE_VALUE_BLACK)
                .apply()
    }

    @Test
    fun testGetAllPlate() {

        val allPlateInit = dataManager.getCurrentPlates().blockingGet()

        Assert.assertEquals("Testing prefilled Database", allPlateInit, platesList)
    }

    @Test
    fun testGetAllPlateWithPrefEdit() {
        val newPlateValue = 900f
        sharedPreferences.edit()
                .putFloat(application.getString(R.string.pref_green_plate), newPlateValue).apply()

        val allPlateInit = dataManager.getCurrentPlates().blockingGet()

        Assert.assertEquals("Testing prefilled pref edited Database", allPlateInit[0].plateType.value, newPlateValue)

    }

    @After
    fun close() {
        appDatabase.close()
    }
}
