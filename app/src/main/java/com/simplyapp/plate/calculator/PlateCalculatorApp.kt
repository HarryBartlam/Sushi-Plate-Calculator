package com.simplyapp.plate.calculator

import android.app.Activity
import android.app.Application
import android.preference.PreferenceManager
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_BLUE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GOLD
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GRAY
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_GREEN
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_ORANGE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PINK
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_PURPLE
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_VALUE_SUMO
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import timber.log.Timber
import javax.inject.Inject

open class PlateCalculatorApp : Application(), HasDispatchingActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    val preferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }

    private val component = createComponent()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component.inject(this)

        if (preferences.getInt(this.getString(R.string.pref_first_opened), 0) != BuildConfig.VERSION_CODE) {
            preferences.edit()
                    .putInt(this.getString(R.string.pref_first_opened), BuildConfig.VERSION_CODE)
                    .putFloat(this.getString(R.string.pref_green_plate), PLATE_VALUE_GREEN)
                    .putFloat(this.getString(R.string.pref_blue_plate), PLATE_VALUE_BLUE)
                    .putFloat(this.getString(R.string.pref_purple_plate), PLATE_VALUE_PURPLE)
                    .putFloat(this.getString(R.string.pref_orange_plate), PLATE_VALUE_ORANGE)
                    .putFloat(this.getString(R.string.pref_pink_plate), PLATE_VALUE_PINK)
                    .putFloat(this.getString(R.string.pref_gray_plate), PLATE_VALUE_GRAY)
                    .putFloat(this.getString(R.string.pref_gold_plate), PLATE_VALUE_GOLD)
                    .putFloat(this.getString(R.string.pref_sumo_plate), PLATE_VALUE_SUMO)
                    .apply()
        }

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    protected open fun createComponent(): BaseAppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun component(): BaseAppComponent {
        return component
    }
}
