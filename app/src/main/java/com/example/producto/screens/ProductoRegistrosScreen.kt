package com.example.producto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.producto.Arranque
import com.example.producto.database.ProductoEntidad
import com.example.producto.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ProductoRegistrosScreen(navController: NavController){

    val productos = remember { mutableStateListOf<ProductoEntidad>() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val loadedProductos = Arranque.room.getProductoDao().getAllProductos()
            productos.addAll(loadedProductos)
        }
    }

    if (productos.isNotEmpty()) {
        Column {
            Card(modifier = Modifier
                .fillMaxWidth()) {
                Row(modifier = Modifier.background(color = Color.Transparent).fillMaxWidth()
                    .padding(horizontal = 30.dp)) {
                    Text(text = "Registro de datos")

                }

            }
            productos.forEach { producto ->
                ProductoData(navController, producto)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } else {
        Text(text = "No hay productos registrados")
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoData(navController: NavController, producto: ProductoEntidad) {
    Card(
        onClick = {println(producto.id);navController.navigate("${AppScreens.ProductoDetalle.route}/${producto.id}")}
        ,modifier = Modifier
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
        }
    }




}
