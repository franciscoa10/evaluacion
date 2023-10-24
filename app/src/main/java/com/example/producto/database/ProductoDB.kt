package com.example.producto.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductoEntidad::class], version = 1)
abstract class ProductoDB: RoomDatabase(){
    abstract fun getProductoDao(): ProductoDao
}