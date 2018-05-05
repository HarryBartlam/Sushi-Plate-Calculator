package com.simplyapp.plate.calculator.ui.calculator

import android.app.Application
import android.preference.PreferenceManager
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.manager.DataManager
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.ui.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CalculatorPresenter @Inject constructor(calculatorView: CalculatorMvp.View,
                                              val context: Application,
                                              private val dataManager: DataManager) : BasePresenter<CalculatorMvp.View>(calculatorView), CalculatorMvp.Presenter {

    var currentPlatesTotal: Float = 0f
    var currentDiscountPercent: Int = 0

    val preferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }
    override fun onCreate() {
        view?.setAnimate(preferences.getBoolean(context.getString(R.string.pref_animation), true))
        view?.setDiscount(getDiscount())

        subscription.add(
                dataManager.getCurrentPlates()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ plates ->
                            plateValueChanged(plates
                                    .map { it.numberOfPlates * it.plateType.value }
                                    .reduce { acc, i -> acc + i })

                            view?.setPlates(plates)
                        }, { e ->
                            Timber.e(e)
                            view?.showError()
                        })
        )

    }

    override fun getDiscount(): Int {
        this.currentDiscountPercent = preferences.getInt(context.getString(R.string.pref_discount), 0)
        return currentDiscountPercent
    }

    override fun setDiscount(newDiscount: Int) {
        this.currentDiscountPercent = newDiscount
        preferences.edit()
                .putInt(context.getString(R.string.pref_discount), newDiscount)
                .apply()
        view?.setDiscount(newDiscount)
        plateValueChanged(0f)
    }

    override fun plateValueChanged(plateValue: Float) {
        currentPlatesTotal += plateValue

        view?.setTotal(currentPlatesTotal - ((currentPlatesTotal / 100) * currentDiscountPercent))//todo dicount
    }

    override fun savePlates(plates: List<Plate>) {
        dataManager.savePlates(plates)
    }

    override fun clearPlates(platesToClear: List<Plate>) {
        view?.setPlates(
                platesToClear.map {
                    it.numberOfPlates = 0
                    it
                }
        )
        currentPlatesTotal = 0f
        view?.setTotal(currentPlatesTotal)

    }

}
