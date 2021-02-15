package com.example.seriados.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Episodios (
    val numero : String? = null,
    @PrimaryKey(autoGenerate = true)
    var id:Long? =null
    )