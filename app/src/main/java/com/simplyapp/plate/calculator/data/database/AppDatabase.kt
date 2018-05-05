package com.simplyapp.plate.calculator.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.simplyapp.plate.calculator.data.model.Plate

@Database(entities = arrayOf(Plate::class), version = 2, exportSchema = false)
@TypeConverters(DatabaseTypeAdapter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plateDao(): PlateDao
}