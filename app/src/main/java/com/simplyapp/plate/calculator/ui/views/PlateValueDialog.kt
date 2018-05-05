package com.simplyapp.plate.calculator.ui.views

import android.content.Context
import android.support.v7.app.AppCompatDialog
import android.view.Window
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.utils.androidExt.hideKeyboard
import com.simplyapp.plate.calculator.utils.androidExt.showKeyboard
import kotlinx.android.synthetic.main.dialog_value.*
import java.text.DecimalFormat

class PlateValueDialog(context: Context, title: String, message: String, startValue: Float, saveValue: (Float) -> Unit) : AppCompatDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_value)

        dialog_title.text = title
        dialog_message.text = message
        dialog_value_edit_text.setText(DecimalFormat("0.00").format(startValue))
        dialog_value_edit_text.post {
            showKeyboard(dialog_value_edit_text)
        }

        dialog_cancel.setOnClickListener { dismiss() }
        dialog_save.setOnClickListener {
            saveValue(dialog_value_edit_text.text.toString().toFloat())
            dismiss()
        }

        setOnDismissListener { hideKeyboard() }
    }

}
