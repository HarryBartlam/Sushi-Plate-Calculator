package com.simplyapp.plate.calculator.data.database

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import com.simplyapp.plate.calculator.data.model.PlateConstants
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PLATE_TABLE_NAME
import com.simplyapp.plate.calculator.data.model.PlateConstants.Companion.PRE_FILL_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Provides
    @Singleton
    fun provideAppDatabase(RoomDBBuilder: RoomDatabase.Builder<AppDatabase>): AppDatabase =
            RoomDBBuilder.build()

    @Provides
    @Singleton
    fun provideAppDatabaseBuilder(application: Application): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder(application, AppDatabase::class.java, "sushi_plate_db")
                .addMigrations(MIGRATION_1_2)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        db.execSQL(PRE_FILL_DATABASE)
                    }
                })
    }

    //Removing Black Plate
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DELETE FROM ${PLATE_TABLE_NAME} WHERE ${PlateConstants.PLATE_ID}=${PlateConstants.PLATE_ID_BLACK}")
        }
    }
    @Provides
    @Singleton
    fun providesPlateDao(database: AppDatabase) = database.plateDao()

}
