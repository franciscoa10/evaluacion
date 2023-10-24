package com.example.producto.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductoEntidad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val Nombre: String,
    @ColumnInfo(name = "descripcion") val Descripcion: String,
    @ColumnInfo(name = "stock") val Stock: Int,
    @ColumnInfo(name = "empresa") val Empresa: String,

    )
