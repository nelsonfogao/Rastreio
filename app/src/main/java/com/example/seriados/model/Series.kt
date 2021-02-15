package com.example.seriados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Series (
    val nome: String? = null,
    val categoria: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
){
    override fun toString(): String = " $id - $nome"
}