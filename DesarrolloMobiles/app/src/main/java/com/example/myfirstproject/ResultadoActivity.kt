package com.example.myfirstproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstproject.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) { // pantalla de resultado
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera valores enviados desde MainActivity (usando las mismas claves)
        val monto = intent.getDoubleExtra(getString(R.string.key_monto), 0.0)
        val meses = intent.getIntExtra(getString(R.string.key_meses), 0)

        // Calcula el total en esta pantalla (regla simple: monto * meses)
        val total = monto * meses

        // Muestra el total formateado y un mensaje motivador
        binding.tvTotal.text = getString(R.string.fmt_total, total)
        binding.tvMotiva.text = getString(R.string.msg_motivacion)

        // FAB para "reiniciar": cierra esta Activity y vuelve a la principal
        binding.fabReiniciar.setOnClickListener { finish() }
    }
}