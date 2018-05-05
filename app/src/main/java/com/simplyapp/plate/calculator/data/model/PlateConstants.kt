package com.simplyapp.plate.calculator.data.model

class PlateConstants {
    companion object {

        const val PLATE_TABLE_NAME = "sushi_plates"
        const val PLATE_ID = "_id"
        const val PLATE_NAME = "plates_name"

        const val PLATE_NUMBER = "plates_number"
        const val PLATE_TYPE = "plates_type"

        const val PLATE_VALUE_GREEN = 2.10f
        const val PLATE_VALUE_BLUE = 2.80f
        const val PLATE_VALUE_PURPLE = 3.70f
        const val PLATE_VALUE_ORANGE = 4.20f
        const val PLATE_VALUE_PINK = 4.60f
        const val PLATE_VALUE_GRAY = 5.20f
        const val PLATE_VALUE_GOLD = 6.00f
        const val PLATE_VALUE_SUMO = 9.00f
        const val PLATE_VALUE_BLACK = 2.20f

        const val PLATE_ID_GREEN = 0
        const val PLATE_ID_BLUE = 1
        const val PLATE_ID_PURPLE = 2
        const val PLATE_ID_ORANGE = 3
        const val PLATE_ID_PINK = 4
        const val PLATE_ID_GRAY = 5
        const val PLATE_ID_GOLD = 6
        const val PLATE_ID_SUMO = 7
        const val PLATE_ID_BLACK = 8

        val PRE_FILL_DATABASE = """INSERT INTO $PLATE_TABLE_NAME ($PLATE_ID, $PLATE_NAME, $PLATE_NUMBER, $PLATE_TYPE)
                            VALUES
                            (${PLATE_ID_GREEN}, "Green Plate", 0, "${PlateType.GREEN_PLATE}"),
                            (${PLATE_ID_BLUE}, "Blue Plate", 0, "${PlateType.BLUE_PLATE}"),
                            (${PLATE_ID_PURPLE}, "Purple Plate", 0, "${PlateType.PURPLE_PLATE}"),
                            (${PLATE_ID_ORANGE}, "Orange Plate", 0, "${PlateType.ORANGE_PLATE}"),
                            (${PLATE_ID_PINK}, "Pink Plate", 0, "${PlateType.PINK_PLATE}"),
                            (${PLATE_ID_GRAY}, "Gray Plate", 0, "${PlateType.GRAY_PLATE}"),
                            (${PLATE_ID_GOLD}, "Gold Plate", 0, "${PlateType.GOLD_PLATE}"),
                            (${PLATE_ID_SUMO}, "Sumo Plate", 0, "${PlateType.SUMO_PLATE}")
                        """.trimIndent()
    }
}