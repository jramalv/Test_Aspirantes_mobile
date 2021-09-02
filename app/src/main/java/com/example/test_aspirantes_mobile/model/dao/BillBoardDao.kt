package com.example.test_aspirantes_mobile.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_aspirantes_mobile.model.models.BillBoardTable

@Dao
interface BillBoardDao {
    @Query("SELECT * FROM BillBoardTable")
    fun getBillBoard(): LiveData<List<BillBoardTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(acumulacionCierre: BillBoardTable)

    @Query("DELETE FROM BillBoardTable")
    fun deleteAll()
}