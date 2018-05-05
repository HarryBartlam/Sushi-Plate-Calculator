package com.simplyapp.plate.calculator.data

import android.app.Application
import android.content.SharedPreferences
import com.simplyapp.plate.calculator.data.api.ApiModule
import com.simplyapp.plate.calculator.data.database.DatabaseModule
import com.simplyapp.plate.calculator.data.database.PlateDao
import com.simplyapp.plate.calculator.data.manager.DataManager
import com.simplyapp.plate.calculator.data.manager.DataManagerImpl
import com.simplyapp.plate.calculator.data.preferences.PreferenceModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ApiModule::class, PreferenceModule::class, DatabaseModule::class))
class DataModule {

    @Provides
    @Singleton
    fun provideDataManager(plateDao: PlateDao, sharedPreferences: SharedPreferences, context: Application): DataManager = DataManagerImpl(plateDao, sharedPreferences, context)
}
