package com.cibertec.mimeta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.mimeta.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas sin findViewById
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { // se crea la pantalla
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // infla el XML
        setContentView(binding.root) // dibuja la vista

        // Click del botón: valida, lee datos y navega a ResultadoActivity
        binding.btnCalcular.setOnClickListener {
            val montoTxt = binding.etMonto.text?.toString()?.trim().orEmpty() // lee monto
            val mesesTxt = binding.etMeses.text?.toString()?.trim().orEmpty() // lee meses

            // Validaciones simples mostrando error en los TextInputLayout
            var ok = true
            if (montoTxt.isEmpty()) { binding.tilMonto.error = getString(R.string.msg_campo_requerido); ok = false }
            else binding.tilMonto.error = null
            if (mesesTxt.isEmpty()) { binding.tilMeses.error = getString(R.string.msg_campo_requerido); ok = false }
            else binding.tilMeses.error = null
            if (!ok) {
                Snackbar.make(binding.root, getString(R.string.msg_campo_requerido), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener // detiene si falta algo
            }

            // Convierte a números de forma segura
            val monto = montoTxt.toDoubleOrNull()
            val meses = mesesTxt.toIntOrNull()
            if (monto == null || meses == null) {
                Snackbar.make(binding.root, "Verifica los formatos.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crea Intent y envía datos con claves definidas en strings.xml
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra(getString(R.string.key_monto), monto)
                putExtra(getString(R.string.key_meses), meses)
            }
            startActivity(intent) // navega
        }
    }

    // Callbacks de ciclo de vida con logs para observar en Logcat
    override fun onStart() { super.onStart(); Log.d("CICLO", "MainActivity.onStart") }
    override fun onResume() { super.onResume(); Log.d("CICLO", "MainActivity.onResume") }
    override fun onPause() { super.onPause(); Log.d("CICLO", "MainActivity.onPause") }
}
