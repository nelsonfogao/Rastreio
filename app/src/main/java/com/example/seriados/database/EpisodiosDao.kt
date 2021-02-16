package com.example.seriados.database

import androidx.room.*
import com.example.seriados.model.Episodios
import com.example.seriados.model.Series
import com.example.seriados.model.SeriesEEpi

@Dao
interface EpisodiosDao {

    @Insert     // Create
    suspend fun create(episodios: Episodios)

    @Query("SELECT * FROM Episodios WHERE seriesId = :key")   // Read
    suspend fun read(key: Long): List<Episodios>

    @Update
    suspend fun update(episodios: Episodios)        // id != null

    @Delete
    suspend fun delete(episodios: Episodios)

    @Query("SELECT * FROM Episodios")
    suspend fun all(): List<SeriesEEpi>
}