package com.example.test_aspirantes_mobile.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.test_aspirantes_mobile.model.dao.BillBoardDao
import com.example.test_aspirantes_mobile.model.models.BillBoardTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [BillBoardTable::class], version = 1)
abstract class CinemaDatabase : RoomDatabase() {
    abstract fun billBoardDao(): BillBoardDao

    companion object {
        @Volatile
        private var INSTANCE: CinemaDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CinemaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CinemaDatabase::class.java,
                    "Cinema_database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(RankingDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }


        private class RankingDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {}
                }
            }
        }


    }
}
