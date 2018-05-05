package com.simplyapp.plate.calculator.data.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simplyapp.plate.calculator.data.model.PlateType

class DatabaseTypeAdapter {
    private val plateTypeToken = object : TypeToken<PlateType>() {}.type

    private val gson = Gson()

    @TypeConverter
    fun fromPlateType(value: PlateType?): String? {
        return if (value == null) null else gson.toJson(value, plateTypeToken)
    }

    @TypeConverter
    fun toPlateType(value: String?): PlateType? {
        return if (value == null) null else gson.fromJson(value, plateTypeToken)
    }

}
