package com.cibertec.minitienda

import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibertec.minitienda.databinding.ActivityMainBinding
import com.cibertec.minitienda.databinding.DialogFormProductoBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val productos = mutableListOf<Product>()
    private lateinit var adapter: ProductAdapter

    // Para seleccionar imagen en el diálogo
    private var dialogBinding: DialogFormProductoBinding? = null
    private var pickedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            pickedImageUri = uri
            dialogBinding?.imgPreview?.setImageURI(uri)
        }
    }

    // Generador simple de IDs
    private var nextId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Spinner de Estado (Nuevo/Usado) desde recursos
        ArrayAdapter.createFromResource(
            this,
            R.array.estado_items,
            android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spEstado.adapter = ad
        }

        // RecyclerView
        adapter = ProductAdapter(productos) { updateResumen() }
        binding.rvProductos.layoutManager = LinearLayoutManager(this)
        binding.rvProductos.adapter = adapter

        // Botón Agregar -> abre diálogo de formulario
        binding.btnAgregar.setOnClickListener {
            openNuevoProductoDialog()
        }

        // Resumen inicial
        updateResumen()
    }

    private fun openNuevoProductoDialog() {
        val b = DialogFormProductoBinding.inflate(layoutInflater)
        dialogBinding = b
        pickedImageUri = null

        // Lista de categorías
        val cats = resources.getStringArray(R.array.categorias_items)
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, cats)
        b.lvCategorias.adapter = listAdapter
        b.lvCategorias.choiceMode = android.widget.ListView.CHOICE_MODE_SINGLE
        b.lvCategorias.setItemChecked(0, true) // preselección

        // Tocar la imagen para elegir desde galería
        b.imgPreview.setOnClickListener { pickImage.launch("image/*") }

        val dlg = AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_titulo_form))
            .setView(b.root)
            .setPositiveButton(getString(R.string.btn_guardar), null) // lo seteamos manualmente luego
            .setNegativeButton(getString(R.string.btn_cancelar)) { d, _ ->
                dialogBinding = null
                pickedImageUri = null
                d.dismiss()
            }
            .create()

        dlg.setOnShowListener {
            dlg.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                // Validaciones (usando TextInputLayout.setError)
                val nombre = b.edtNombre.text?.toString()?.trim().orEmpty()
                val precio = b.edtPrecio.text?.toString()?.trim()?.replace(",", ".")?.toDoubleOrNull()
                val cantidad = b.edtCantidad.text?.toString()?.trim()?.toIntOrNull()

                var ok = true
                b.tilNombre.error = null
                b.tilPrecio.error = null
                b.tilCantidad.error = null

                if (nombre.isEmpty()) {
                    b.tilNombre.error = getString(R.string.error_nombre_requerido)
                    ok = false
                }
                if (precio == null || precio <= 0.0) {
                    b.tilPrecio.error = getString(R.string.error_precio_requerido)
                    ok = false
                }
                if (cantidad == null || cantidad <= 0) {
                    b.tilCantidad.error = getString(R.string.error_cantidad_requerida)
                    ok = false
                } else if (cantidad > 100) {
                    b.tilCantidad.error = getString(R.string.error_cantidad_max)
                    ok = false
                }

                if (!ok) return@setOnClickListener

                // Estado desde Spinner principal
                val estado = binding.spEstado.selectedItem?.toString().orEmpty()

                // Categoría seleccionada del ListView
                val posCat = b.lvCategorias.checkedItemPosition.takeIf { it >= 0 } ?: 0
                val categoria = cats[posCat]

                // Disponible
                val disponible = b.switchDisponible.isChecked

                // Crear y agregar producto
                val p = Product(
                    id = nextId++,
                    nombre = nombre,
                    precio = precio!!,
                    cantidad = cantidad!!,
                    estado = estado,
                    categoria = categoria,
                    disponible = disponible,
                    imageUri = pickedImageUri
                )
                adapter.add(p)

                // Limpieza y cerrar
                dialogBinding = null
                pickedImageUri = null
                dlg.dismiss()
            }
        }

        dlg.show()
    }

    private fun updateResumen() {
        val subtotal = productos.sumOf { it.precio * it.cantidad }
        val igv = subtotal * 0.18
        val total = subtotal + igv

        val pref = getString(R.string.moneda_prefijo)
        binding.txtResumen.text = buildString {
            appendLine(getString(R.string.lbl_subtotal, pref, money(subtotal)))
            appendLine(getString(R.string.lbl_igv, pref, money(igv)))
            append(getString(R.string.lbl_total, pref, money(total)))
        }
    }

    private fun money(n: Double): String {
        val loc = Locale("es", "PE")
        return String.format(loc, "%,.2f", n)
    }
}
