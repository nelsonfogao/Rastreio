package com.example.seriados.ui.alterar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriados.database.EpisodiosDao
import com.example.seriados.database.EpisodiosUtil
import com.example.seriados.database.SeriesDao
import com.example.seriados.model.Episodios
import kotlinx.coroutines.launch

class EpisodiosViewModel (
    private val episodiosDao: EpisodiosDao,
    private val seriesDao: SeriesDao
) : ViewModel() {


    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        _status.value = false
        _msg.value = null
    }

    fun salvarEpisodios(numero:String, serieId: Long) {
        _status.value = false
        _msg.value = "Por favor, aguarde a persistência!"



        viewModelScope.launch {
            try {

                val episodio = Episodios(numero)

                if (EpisodiosUtil.episodioSelecionado != null) {
                    episodio.id = EpisodiosUtil.episodioSelecionado!!.id
                    episodiosDao.update(episodio)
                } else
                    episodiosDao.create(episodio)

                _status.value = true
                _msg.value = "Persistência realizada!"
            } catch (e: Exception) {
                _msg.value = "Persistência falhou!"
            }
        }
    }
}