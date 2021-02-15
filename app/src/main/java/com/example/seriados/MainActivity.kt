package com.example.seriados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seriados.database.AppDatabase
import com.example.seriados.model.Episodios
import com.example.seriados.model.Series
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}