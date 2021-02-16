package com.example.seriados.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Episodios (
    var numero : String? = null,
    var seriesId: Long? = null,
    @PrimaryKey(autoGenerate = true)
    var id:Long? =null
    ){
    override fun toString(): String = " $numero"
}