package com.example.test_aspirantes_mobile.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.test_aspirantes_mobile.model.CinemaDatabase
import com.example.test_aspirantes_mobile.model.models.BillBoardTable
import com.example.test_aspirantes_mobile.model.repository.BillBoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BillBoardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BillBoardRepository
    val allBillBoard: LiveData<List<BillBoardTable>>

    init {
        val billBoardDao =
            CinemaDatabase.getDatabase(application, viewModelScope).billBoardDao()
        repository = BillBoardRepository(billBoardDao)
        allBillBoard = repository.allBillBoard
    }

    fun insert(billBoardTable: BillBoardTable) =
        viewModelScope.launch(Dispatchers.IO) { repository.insert(billBoardTable) }

    fun delete() = viewModelScope.launch(Dispatchers.IO) { repository.delete() }
}