package com.example.seriados.ui.alterar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seriados.database.EpisodiosDao
import com.example.seriados.database.SeriesDao

class EpisodiosViewModelFactory (
    val episodiosDao: EpisodiosDao,
    val seriesDao: SeriesDao
    ) : ViewModelProvider.Factory
    {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EpisodiosViewModel::class.java)) {
                return EpisodiosViewModel(episodiosDao, seriesDao) as T
            }
            throw IllegalArgumentException("Classe ViewModel desconhecida.")
        }
    }