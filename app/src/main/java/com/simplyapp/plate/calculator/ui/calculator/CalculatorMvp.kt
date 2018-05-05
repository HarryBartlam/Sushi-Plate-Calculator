package com.simplyapp.plate.calculator.ui.calculator

import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.ui.BaseMvp

interface CalculatorMvp : BaseMvp {

    interface View : BaseMvp.View {
        fun showError()
        fun setPlates(plates: List<Plate>)
        fun setTotal(plateTotal: Float)
        fun setAnimate(animate: Boolean)
        fun setDiscount(discount: Int)
    }

    interface Presenter : BaseMvp.Presenter {
        fun onCreate()
        fun plateValueChanged(plateValue: Float)
        fun savePlates(plates: List<Plate>)
        fun clearPlates(platesToClear: List<Plate>)
        fun getDiscount(): Int
        fun setDiscount(newDiscount: Int)
    }
}
