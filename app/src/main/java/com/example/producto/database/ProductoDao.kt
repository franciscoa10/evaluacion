package com.example.producto.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductoDao {

    @Query("SELECT * FROM productos" )
    suspend fun getAllProductos():List<ProductoEntidad>

    @Query("SELECT * FROM productos WHERE id = :id" )
    suspend fun getProductoById(id: Int):ProductoEntidad

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(productos: ProductoEntidad)
}