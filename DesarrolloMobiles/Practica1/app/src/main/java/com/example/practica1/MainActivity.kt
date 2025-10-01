package com.example.practica1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            val txttalla = binding.etTalla.text?.toString()?.trim().orEmpty()
            val txtpeso = binding.etPeso.text?.toString()?.trim().orEmpty()

            var ok = true

            // Validación de campos vacíos
            binding.titTalla.error = if (txttalla.isEmpty()) {
                ok = false
                getString(R.string.msg_campo_requerido)
            } else {
                null
            }

            binding.titPeso.error = if (txtpeso.isEmpty()) {
                ok = false
                getString(R.string.msg_campo_requerido)
            } else {
                null
            }

            // Si algún campo está vacío, muestra el mensaje de error
            if (!ok) {
                Snackbar.make(binding.root, getString(R.string.msg_campo_requerido), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Conversión de texto a números
            val talla = txttalla.toDoubleOrNull()
            val peso = txtpeso.toDoubleOrNull()

            // Validación para asegurarse de que los valores sean numéricos
            if (talla == null || peso == null) {
                Snackbar.make(binding.root, "VERIFICAR LOS VALORES DEBEN SER NUMERICOS", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Intent para pasar los datos al siguiente activity
            val intent = Intent(this, ResultadoIMC::class.java).apply {
                putExtra(getString(R.string.key_peso), peso)
                putExtra(getString(R.string.key_talla), talla)
            }
            startActivity(intent)
        }
    }
}
