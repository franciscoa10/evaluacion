package com.example.producto

import android.app.Application
import androidx.room.Room
import com.example.producto.database.ProductoDB

class Arranque: Application() {

    companion object{
        lateinit var room: ProductoDB
    }

    override fun onCreate() {
        super.onCreate()
        room = Room
            .databaseBuilder(applicationContext, ProductoDB::class.java, "producto_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}