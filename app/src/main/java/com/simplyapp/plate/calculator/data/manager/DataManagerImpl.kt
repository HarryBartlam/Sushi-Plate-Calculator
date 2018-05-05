package com.simplyapp.plate.calculator.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.database.PlateDao
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.data.model.PlateConstants
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import kotlin.concurrent.thread

class DataManagerImpl @Inject constructor(val plateDao: PlateDao,
                                          val sharedPreferences: SharedPreferences,
                                          val context: Context) : DataManager {

    override fun getCurrentPlates(): Single<List<Plate>> {

        return plateDao.getAllPlates()
                .map { initPlates(it) }
    }

    private fun initPlates(databasePlateList: List<Plate>): List<Plate> {
        databasePlateList.forEach { plate ->
            when (plate.id) {
                PlateConstants.PLATE_ID_GREEN -> setSavedPlateValue(plate, context.getString(R.string.pref_green_plate))
                PlateConstants.PLATE_ID_BLUE -> setSavedPlateValue(plate, context.getString(R.string.pref_blue_plate))
                PlateConstants.PLATE_ID_PURPLE -> setSavedPlateValue(plate, context.getString(R.string.pref_purple_plate))
                PlateConstants.PLATE_ID_ORANGE -> setSavedPlateValue(plate, context.getString(R.string.pref_orange_plate))
                PlateConstants.PLATE_ID_PINK -> setSavedPlateValue(plate, context.getString(R.string.pref_pink_plate))
                PlateConstants.PLATE_ID_GRAY -> setSavedPlateValue(plate, context.getString(R.string.pref_gray_plate))
                PlateConstants.PLATE_ID_GOLD -> setSavedPlateValue(plate, context.getString(R.string.pref_gold_plate))
                PlateConstants.PLATE_ID_SUMO -> setSavedPlateValue(plate, context.getString(R.string.pref_sumo_plate))
                PlateConstants.PLATE_ID_BLACK -> setSavedPlateValue(plate, context.getString(R.string.pref_black_plate))
                else ->
                    Timber.e("There are more plates in the database then there should be....fix that")
            }
        }
        return databasePlateList
    }

    private fun setSavedPlateValue(plate: Plate, prefId: String): Plate {
        val value = plate.plateType!!.value
        val newValue = sharedPreferences.getFloat(prefId, value)
        if (!newValue.equals(value)) {
            plate.plateType!!.value = newValue
        }
        return plate
    }

    override fun savePlates(plates: List<Plate>) {
        plates.let {
            thread {
                plateDao.insertPlate(plates)
            }
        }
    }
}