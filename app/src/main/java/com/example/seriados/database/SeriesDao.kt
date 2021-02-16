package com.example.seriados.database

import androidx.room.*
import com.example.seriados.model.Series
import com.example.seriados.model.SeriesEEpi

@Dao
interface SeriesDao {

    @Insert     // Create
    suspend fun create(series: Series)

    @Transaction
    @Query("SELECT * FROM Series WHERE id = :key")   // Read
    suspend fun read(key: Long): Series

    @Update
    suspend fun update(series: Series)        // id != null

    @Delete
    suspend fun delete(series: Series)

    @Query("SELECT * FROM Series")
    suspend fun all(): List<Series>

}