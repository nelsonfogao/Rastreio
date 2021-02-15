package com.example.seriados.database

import androidx.room.*
import com.example.seriados.model.Episodios

@Dao
interface EpisodiosDao {

    @Insert     // Create
    suspend fun create(episodios: Episodios)

    @Update
    suspend fun update(episodios: Episodios)        // id != null

    @Delete
    suspend fun delete(episodios: Episodios)

}