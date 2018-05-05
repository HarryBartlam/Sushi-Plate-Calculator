package com.simplyapp.plate.calculator.data.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_BLACK
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_BLUE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GOLD
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GRAY
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GREEN
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_ORANGE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PINK
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PURPLE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_SUMO

enum class PlateType(@ColorRes var color: Int, @DrawableRes var image: Int = R.drawable.small_plate, var value: Float) {
    GREEN_PLATE(R.color.plate_green, R.drawable.small_plate, PLATE_VALUE_GREEN),
    BLUE_PLATE(R.color.plate_blue, R.drawable.small_plate, PLATE_VALUE_BLUE),
    PURPLE_PLATE(R.color.plate_purple, R.drawable.small_plate, PLATE_VALUE_PURPLE),
    ORANGE_PLATE(R.color.plate_orange, R.drawable.small_plate, PLATE_VALUE_ORANGE),
    PINK_PLATE(R.color.plate_pink, R.drawable.small_plate, PLATE_VALUE_PINK),
    GRAY_PLATE(R.color.plate_gray, R.drawable.small_plate, PLATE_VALUE_GRAY),
    GOLD_PLATE(R.color.plate_gold, R.drawable.small_plate, PLATE_VALUE_GOLD),
    SUMO_PLATE(R.color.plate_black, R.drawable.big_plate, PLATE_VALUE_SUMO),
    BLACK_PLATE(R.color.plate_black, R.drawable.small_plate, PLATE_VALUE_BLACK),
    ERROR_PLATE(R.color.color_accent, R.mipmap.ic_launcher, 999f);
}
