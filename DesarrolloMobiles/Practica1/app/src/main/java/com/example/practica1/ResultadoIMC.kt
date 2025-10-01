package com.example.practica1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica1.databinding.ActivityResultadoImcBinding

class ResultadoIMC : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoImcBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultadoImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peso=intent.getDoubleExtra(getString(R.string.key_peso),0.0)
        val talla=intent.getDoubleExtra(getString(R.string.key_talla),0.0)

        var imc=peso/(talla*talla)

        when{
            imc > 32 -> binding.tvMensaje.text = getString(R.string.msg_flaco)
            imc in 24.0..32.0 -> binding.tvMensaje.text = getString(R.string.msg_normal)
            imc < 24 -> binding.tvMensaje.text = getString(R.string.msg_gordo)
        }
        val numero=1

        binding.tvResultado.text=getString(R.string.fmt_resultado,imc)
        binding.floatingActionButton.setOnClickListener{finish()}

    }
}