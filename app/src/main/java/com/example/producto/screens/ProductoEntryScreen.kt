package com.example.producto.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.producto.Arranque.Companion.room
import com.example.producto.database.ProductoEntidad
import com.example.producto.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoEntryScreen (navController: NavController){
    var nombre = remember { mutableStateOf("") }
    var descripcion = remember { mutableStateOf("") }
    var stock = remember { mutableStateOf("") }
    var empresa = remember { mutableStateOf("") }


    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.LightGray,
        contentColor = Color.Black,
        disabledContainerColor = Color.LightGray,
        disabledContentColor = Color.Black
    )

    val db = room

    val scope = rememberCoroutineScope()

    Card(modifier = Modifier
        .fillMaxWidth()) {
        Row(modifier = Modifier.background(color = Color.Transparent)
            .padding(horizontal = 30.dp)) {
            Text(text = "Ingreso de datos")

        }

    }
    Column(
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .zIndex(4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(10.dp)) {
                Text(text = "Anchundia Santana Francisco Gregorio", Modifier.padding(4.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Producto", Modifier.padding(4.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Septimo B", Modifier.padding(4.dp))
            }
        }
        TextField(
            value = nombre.value,
            singleLine = true,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(5.dp)),
        )

        TextField(
            value = descripcion.value,
            singleLine = true,
            onValueChange = { descripcion.value = it },
            label = { Text("Descripcion", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(5.dp)),
        )

        TextField(
            value = stock.value,
            onValueChange = { stock.value = it },
            label = { Text("Stock") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(5.dp)),
        )

        TextField(
            value = empresa.value,
            singleLine = true,
            onValueChange = { empresa.value = it },
            label = { Text("Empresa", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(5.dp)),
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = {
                    if (nombre.value.isBlank() || descripcion.value.isBlank() || stock.value.isBlank() ||
                        empresa.value.isBlank()
                    ) {

                    } else {
                        val producto = ProductoEntidad(
                            Nombre = nombre.value,
                            Descripcion = descripcion.value,
                            Stock = stock.value.toInt(),
                            Empresa = empresa.value
                        )
                        scope.launch {
                            withContext(Dispatchers.Main) {
                                db.getProductoDao().create(producto)
                                nombre.value = ""
                                descripcion.value = ""
                                stock.value = ""
                                empresa.value = ""
                            }
                        }

                    }

                },
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 0.dp),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text("Crear producto")
            }
        }
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxHeight()) {
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ProductoRegistros.route)
                },
                colors = buttonColors,
                modifier = Modifier.align(Alignment.Bottom),
                border = BorderStroke(0.dp, Color.Transparent)
            ) {
                Text("Ver productos creados")
            }

        }


    }





}
