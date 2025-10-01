package com.cibertec.minitienda

import android.net.Uri

data class Product(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var cantidad: Int,
    val estado: String,      // "Nuevo" / "Usado"
    val categoria: String,   // tomada del ListView del diálogo
    val disponible: Boolean,
    val imageUri: Uri? = null
)
