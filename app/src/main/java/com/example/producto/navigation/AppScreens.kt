package com.example.producto.navigation

sealed class AppScreens(val route: String){

    object ProductoEntry: AppScreens("ProductoEntry")
    object ProductoRegistros: AppScreens("ProductoRegistros")
    object ProductoDetalle: AppScreens("ProductoDetalle/{productoId}")

}