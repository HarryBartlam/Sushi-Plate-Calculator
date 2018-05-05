package com.simplyapp.plate.calculator.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_TABLE_NAME
import io.reactivex.Single

@Dao
interface PlateDao {

    @Insert(onConflict = REPLACE)
    fun insertPlate(plate: List<Plate>): LongArray

    @Query("SELECT * FROM $PLATE_TABLE_NAME")
    fun getAllPlates(): Single<List<Plate>>

}

