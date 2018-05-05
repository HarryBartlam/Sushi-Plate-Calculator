package com.simplyapp.plate.calculator.ui.views

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.simplyapp.plate.calculator.PlateCalculatorApp
import com.simplyapp.plate.calculator.R
import kotlinx.android.synthetic.main.view_setting_value.view.*
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class SettingPrefView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    @Inject lateinit var sharedPreferences: SharedPreferences

    val formatter: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(Locale.UK)

    }

    private var prefRes: Int
    private var plateValue: Float = 0.0f
    private var plateChecked: Boolean = true

    init {
        View.inflate(getContext(), R.layout.view_setting_value, this)
        (context.applicationContext as PlateCalculatorApp).component().inject(this)

        var prefRes = 0
        var titleRes = R.string.error
        var subTitleRes = R.string.error
        var mode = 0

        if (attrs != null) {
            val array = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.SettingValueView,
                    defStyleAttr, 0)
            try {
                prefRes = array.getResourceId(R.styleable.SettingValueView_prefRes, prefRes)
                titleRes = array.getResourceId(R.styleable.SettingValueView_titleRes, titleRes)
                subTitleRes = array.getResourceId(R.styleable.SettingValueView_subtitleRes, subTitleRes)
                mode = array.getInt(R.styleable.SettingValueView_mode, mode)
            } finally {
                array.recycle()
            }
        }

        this.prefRes = prefRes

        setting_view_title.setText(titleRes)
        setting_view_sub_title.setText(subTitleRes)

        when (mode) {
            0 -> {
                setting_view_value.visibility = View.VISIBLE
                setting_view_value.setText(R.string.default_plate_value)
                setting_value_layout.setOnClickListener {

                    PlateValueDialog(context
                            , context.getString(titleRes)
                            , context.getString(subTitleRes)
                            , plateValue, { savePrefStringValue(it) }).show()
                }
                loadPrefStringValue()
            }
            1 -> {
                setting_view_checkbox.visibility = View.VISIBLE
                loadPrefBooleanValue()
                setting_view_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    savePrefStringValue(isChecked)
                }
            }
            else -> {

            }
        }

    }

    private fun loadPrefBooleanValue() {
        plateChecked = sharedPreferences.getBoolean(context.getString(prefRes), true)
        setting_view_checkbox.isChecked = plateChecked
    }

    private fun savePrefStringValue(checked: Boolean) {
        plateChecked = checked
        sharedPreferences.edit().putBoolean(context.getString(prefRes), plateChecked).apply()
        setting_view_checkbox.isChecked = plateChecked
    }

    private fun loadPrefStringValue() {
        plateValue = sharedPreferences.getFloat(context.getString(prefRes), 0f)
        setting_view_value.text = formatter.format(plateValue)
    }

    private fun savePrefStringValue(newFloat: Float) {
        plateValue = newFloat
        sharedPreferences.edit().putFloat(context.getString(prefRes), plateValue).apply()
        setting_view_value.text = formatter.format(plateValue)
    }

}
