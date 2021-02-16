package com.example.seriados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Series (
    var nome: String? = null,
    var categoria: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
){
    override fun toString(): String = " $id - $nome"
}