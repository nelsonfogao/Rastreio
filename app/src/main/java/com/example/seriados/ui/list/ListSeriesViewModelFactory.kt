package com.example.seriados.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seriados.database.SeriesDao

class ListSeriesViewModelFactory (
    private val seriesDao: SeriesDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListSeriesViewModel::class.java))
            return ListSeriesViewModel(seriesDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}