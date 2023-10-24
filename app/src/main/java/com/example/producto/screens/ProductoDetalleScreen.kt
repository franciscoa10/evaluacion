package com.example.producto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.producto.Arranque
import com.example.producto.database.ProductoEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ProductoDetalleScreen(navController: NavController, productoId: String?){

    val productoIdInt: Int = productoId?.toIntOrNull() ?: 0

    val producto = remember { mutableStateListOf<ProductoEntidad>() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val loadedPlanta = Arranque.room.getProductoDao().getProductoById(productoIdInt)
            producto.add(loadedPlanta)
        }
    }

    if (producto.isNotEmpty()) {
        Column {
            Card(modifier = Modifier
                .fillMaxWidth()) {
                Row(modifier = Modifier.background(color = Color.Transparent)
                    .padding(horizontal = 30.dp)) {
                    Text(text = "Registro individual")

                }

            }
            producto.forEach { productoRegistro ->
                ProductoData2(productoRegistro)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } else {
        Text(text = "No se escontró ese producto.")
    }


}


@Composable
fun ProductoData2(producto: ProductoEntidad) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(20.dp)
                .zIndex(4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(text = "ID: ${producto.id}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Valor: ${producto.Nombre}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Descripción: ${producto.Descripcion}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Stock: ${producto.Stock}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Empresa: ${producto.Empresa}")
            }
        }

    }
}



