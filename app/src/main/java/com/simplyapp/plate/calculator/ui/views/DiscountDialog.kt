package com.simplyapp.plate.calculator.ui.views

import android.content.Context
import android.support.v7.app.AppCompatDialog
import android.view.Window
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.utils.androidExt.hideKeyboard
import com.simplyapp.plate.calculator.utils.androidExt.showKeyboard
import kotlinx.android.synthetic.main.dialog_discount.*

class DiscountDialog(context: Context, startingDiscount: Int, saveValue: (Int) -> Unit) : AppCompatDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_discount)

        dialog_discount_edit_text.setText(startingDiscount.toString())
        dialog_discount_edit_text.post {
            showKeyboard(dialog_discount_edit_text)
        }

        dialog_discount_cancel.setOnClickListener { dismiss() }
        dialog_discount_save.setOnClickListener {
            var newDiscount = 0
            if (dialog_discount_edit_text.text.isNotEmpty()) {
                newDiscount = dialog_discount_edit_text.text.toString().toInt()
            }
            saveValue(newDiscount)
            dismiss()
        }

        setOnDismissListener { hideKeyboard() }
    }

}