package com.techkingsley.agromall.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms

@Database(entities = [Farmers::class, Farms::class], version = 1, exportSchema = false)
abstract class FarmersLocalDatabase : RoomDatabase() {

    abstract fun farmersDao(): FarmersDao
    abstract fun farmsDao(): FarmDao

    companion object {
        private lateinit var INSTANCE: FarmersLocalDatabase
        private const val DB_NAME = "Farmers.db"

        fun database(context: Context): FarmersLocalDatabase {
            synchronized(FarmersLocalDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FarmersLocalDatabase::class.java, DB_NAME
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}