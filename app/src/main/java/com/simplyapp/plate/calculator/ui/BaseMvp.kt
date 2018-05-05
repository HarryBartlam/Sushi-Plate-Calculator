package com.simplyapp.plate.calculator.ui

interface BaseMvp {
    interface View {
        fun finish()
    }

    interface Presenter {
        fun onDestroy()
    }
}
