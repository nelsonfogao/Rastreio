package com.example.seriados.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriados.database.EpisodiosDao
import com.example.seriados.database.SeriesDao
import com.example.seriados.database.SeriesUtil
import com.example.seriados.model.Episodios
import com.example.seriados.model.Series
import kotlinx.coroutines.launch

class FormSeriesViewModel (
    private val seriesDao: SeriesDao,
    private val episodiosDao : EpisodiosDao
) : ViewModel() {


    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _episodios = MutableLiveData<List<Episodios>>()
    val episodios: LiveData<List<Episodios>> = _episodios

    init {
        _status.value = false
        _msg.value = null
    }


    fun atualizarListaEpisodios(serieId: Long) {
        viewModelScope.launch {
            _episodios.value =
                episodiosDao.read(serieId)
        }
    }


    fun deletarSerie(serie:Series) {
        _status.value = false
        _msg.value = "Por favor, aguarde a persistência!"



        viewModelScope.launch {
            try {

                val series = serie

                if (SeriesUtil.serieSelecionada != null) {
                    seriesDao.delete(series)
                }

                _status.value = true
                _msg.value = "Deletada!"
            } catch (e: Exception) {
                _msg.value = "não deletada!"
            }
        }
    }


    fun salvarSeries(nome:String, categoria: String) {
        _status.value = false
        _msg.value = "Por favor, aguarde a persistência!"



        viewModelScope.launch {
            try {

                val series = Series(nome, categoria)

                if (SeriesUtil.serieSelecionada != null) {
                    series.id = SeriesUtil.serieSelecionada!!.id
                    seriesDao.update(series)
                } else
                    seriesDao.create(series)

                _status.value = true
                _msg.value = "Persistência realizada!"
            } catch (e: Exception) {
                _msg.value = "Persistência falhou!"
            }
        }
    }
}