package com.example.test_aspirantes_mobile.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.test_aspirantes_mobile.model.dao.BillBoardDao
import com.example.test_aspirantes_mobile.model.models.BillBoardTable

class BillBoardRepository(private val billBoardDao: BillBoardDao) {
    val allBillBoard: LiveData<List<BillBoardTable>> =
        billBoardDao.getBillBoard()

    @WorkerThread
    suspend fun insert(billBoardTable: BillBoardTable) {
        billBoardDao.insert(billBoardTable)
    }

    @WorkerThread
    suspend fun delete() {
        billBoardDao.deleteAll()
    }


}