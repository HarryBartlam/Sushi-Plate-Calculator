package com.simplyapp.plate.calculator.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_ID
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_NAME
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_NUMBER
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_TABLE_NAME
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_TYPE

@Entity(tableName = PLATE_TABLE_NAME)
data class Plate(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = PLATE_ID)
        var id: Int = 0,
        @ColumnInfo(name = PLATE_NAME)
        var name: String = "",
        @ColumnInfo(name = PLATE_NUMBER)
        var numberOfPlates: Int = 0,
        @ColumnInfo(name = PLATE_TYPE)
        var plateType: PlateType = PlateType.ERROR_PLATE)