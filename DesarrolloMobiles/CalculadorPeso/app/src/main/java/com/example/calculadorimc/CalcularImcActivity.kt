package com.example.calculadorimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorimc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class CalcularImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TRAEMOS LOS DATOS, LO INFLAMOS Y PEGAMOS EL CONTENIDO DE LA VISTA
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ESCUCHAMOS LA LLAMADA DEL BOTON
        binding.btnCalcular.setOnClickListener {

            // DECLARACION DE VALORES
            val pesoStr = binding.etPeso.text.toString().trim()
            val tallaStr = binding.etTalla.text.toString().trim()

            // VALIDACIONES
            var ok = true

            // Validar campo peso
            if (pesoStr.isEmpty()) {
                binding.tilPeso.error = getString(R.string.msg_campo_requerido)
                ok = false
            } else {
                binding.tilPeso.error = null
            }

            // Validar campo talla
            if (tallaStr.isEmpty()) {
                binding.tilTalla.error = getString(R.string.msg_campo_requerido)
                ok = false
            } else {
                binding.tilTalla.error = null
            }

            if (!ok) {
                // Mostrar mensaje de error si algún campo está vacío
                Snackbar.make(binding.root, getString(R.string.msg_campo_requerido), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convertir los valores ingresados a números (Float)
            val peso = pesoStr.toFloatOrNull()
            val talla = tallaStr.toFloatOrNull()

            // Verificar si los valores ingresados son válidos
            if (peso == null || talla == null) {
                Snackbar.make(binding.root, "Por favor ingresa valores numéricos válidos", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calcular el IMC
            val imc = peso / (talla * talla)

            // Pasar los datos a la siguiente actividad
            val intent = Intent(this, CalcularImcActivity::class.java).apply {
                putExtra(getString(R.string.key_peso), imc) // Aquí pasas el IMC calculado
                putExtra(getString(R.string.key_talla), imc) // Aquí pasas el IMC calculado
            }
            startActivity(intent)
        }
    }
}
