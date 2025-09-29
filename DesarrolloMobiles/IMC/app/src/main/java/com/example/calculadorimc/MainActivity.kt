package com.example.calculadorimc

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorimc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            val txtTalla=binding.itTalla.text?.toString()?.trim().orEmpty();
            val txtPeso=binding.itPeso.text?.toString()?.trim().orEmpty();

            var ok=true
            if(txtTalla.isEmpty()){
                binding.tilTalla.error=getString(R.string.msg_campo_requerido);
                ok=false
            }else{
                binding.tilTalla.error=null
            }
            if(txtPeso.isEmpty()){
                binding.tilPeso.error=getString(R.string.msg_campo_requerido);
                ok=false
            }else{
                binding.tilTalla.error=null
            }

            if(!ok){
                Snackbar.make(binding.root, getString(R.string.msg_campo_requerido),Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso=txtPeso.toDoubleOrNull();
            val talla=txtTalla.toDoubleOrNull();

            if(peso==null || talla==null){
                Snackbar.make(binding.root,"Varificacion de formato",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (talla > 4) {
                binding.tilTalla.error = getString(R.string.msg_talla)
                return@setOnClickListener

            }

            val intent=Intent(this,ResultadoIMC_Activity::class.java).apply {
                putExtra(getString(R.string.key_peso),peso)
                putExtra(getString(R.string.key_talla),talla)
            }
            startActivity(intent)

        }


        }
}