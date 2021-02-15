package com.example.seriados.model

import androidx.room.Embedded
import androidx.room.Relation

class SeriesEEpi (
    @Embedded val series: Series,

    @Relation(
        parentColumn = "id",
        entityColumn = "seriesId"
    )
    val episodios: Episodios
) {
    override fun toString(): String = "Serie:" +
            "${series.nome}\t" +
            "${episodios.numero}"
}