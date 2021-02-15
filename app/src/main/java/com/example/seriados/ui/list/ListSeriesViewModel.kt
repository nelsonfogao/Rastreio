package com.example.seriados.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriados.database.SeriesDao
import com.example.seriados.model.Series
import kotlinx.coroutines.launch

class ListSeriesViewModel (
    private val seriesDao: SeriesDao
    ) : ViewModel() {

        private val _series = MutableLiveData<List<Series>>()
        val series: LiveData<List<Series>> = _series

        fun atualizarListaSeries() {
            viewModelScope.launch {
                _series.value =
                    seriesDao.all()
            }
        }
}