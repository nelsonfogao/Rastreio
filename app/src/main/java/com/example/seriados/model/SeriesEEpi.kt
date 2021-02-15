package com.example.seriados.model

import androidx.room.Embedded
import androidx.room.Relation

class SeriesEEpi (
    @Embedded val episodios: Episodios,

    @Relation(
        parentColumn = "id",
        entityColumn = "episodioId"
    )
    val series: Series
) {
    override fun toString(): String = "Serie:" +
            "${series.nome}\t" +
            "${episodios.numero}"
}