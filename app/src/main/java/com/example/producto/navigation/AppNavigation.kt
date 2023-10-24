package com.example.producto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.producto.screens.ProductoDetalleScreen
import com.example.producto.screens.ProductoEntryScreen
import com.example.producto.screens.ProductoRegistrosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController =navController , startDestination = AppScreens.ProductoEntry.route){
        composable(route = AppScreens.ProductoEntry.route){
            ProductoEntryScreen(navController)
        }
        composable(route = AppScreens.ProductoRegistros.route){
            ProductoRegistrosScreen(navController)
        }
        composable(route = AppScreens.ProductoDetalle.route + "/{productoId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val productoId = arguments.getString("productoId")
            ProductoDetalleScreen(navController, productoId)
        }


    }

}