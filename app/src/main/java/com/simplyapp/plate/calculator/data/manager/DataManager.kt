package com.simplyapp.plate.calculator.data.manager

import com.simplyapp.plate.calculator.data.model.Plate
import io.reactivex.Single

interface DataManager {
    fun getCurrentPlates(): Single<List<Plate>>
    fun savePlates(plates: List<Plate>)
}