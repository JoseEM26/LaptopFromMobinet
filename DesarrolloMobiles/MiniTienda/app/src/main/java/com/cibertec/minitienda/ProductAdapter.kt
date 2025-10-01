package com.cibertec.minitienda

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minitienda.databinding.ItemProductoBinding
import java.util.Locale

class ProductAdapter(
    private val items: MutableList<Product>,
    private val onListChanged: () -> Unit // avisa a la Activity para recalcular totales
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inf = LayoutInflater.from(parent.context)
        val binding = ItemProductoBinding.inflate(inf, parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val ctx = holder.binding.root.context
        val p = items[position]
        val b = holder.binding

        // Imagen
        if (p.imageUri != null) {
            b.imgThumb.setImageURI(p.imageUri)
        } else {
            b.imgThumb.setImageResource(R.mipmap.ic_launcher)
        }

        // Nombre
        b.txtNombre.text = p.nombre

        // Detalle: "Disponible • Categoria • Estado"
        val disponibleTexto = if (p.disponible) "Disponible" else "No disponible"
        val derecha = "${p.categoria} • ${p.estado}"
        b.txtDetalle.text = ctx.getString(R.string.fmt_detalle, disponibleTexto, derecha)

        // Precio destacado
        b.txtPrecio.text = ctx.getString(R.string.fmt_precio, ctx.getString(R.string.moneda_prefijo), money(p.precio))

        // Menú contextual en long-press
        b.root.setOnLongClickListener {
            val pop = PopupMenu(ctx, b.root)
            pop.menu.add(ctx.getString(R.string.menu_editar_cantidad))
            pop.menu.add(ctx.getString(R.string.menu_eliminar))
            pop.setOnMenuItemClickListener { item ->
                when (item.title) {
                    ctx.getString(R.string.menu_editar_cantidad) -> {
                        showEditCantidadDialog(ctx, p) {
                            notifyItemChanged(position)
                            onListChanged()
                        }
                        true
                    }
                    ctx.getString(R.string.menu_eliminar) -> {
                        val idx = holder.bindingAdapterPosition
                        if (idx != RecyclerView.NO_POSITION) {
                            items.removeAt(idx)
                            notifyItemRemoved(idx)
                            onListChanged()
                        }
                        true
                    }
                    else -> false
                }
            }
            pop.show()
            true
        }
    }

    private fun showEditCantidadDialog(ctx: android.content.Context, p: Product, onDone: () -> Unit) {
        val input = EditText(ctx).apply {
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText(p.cantidad.toString())
            setSelection(text.length)
        }
        AlertDialog.Builder(ctx)
            .setTitle(ctx.getString(R.string.menu_editar_cantidad))
            .setView(input)
            .setPositiveButton(android.R.string.ok) { d, _ ->
                val n = input.text.toString().trim().toIntOrNull()
                if (n == null || n <= 0) {
                    input.error = ctx.getString(R.string.error_cantidad_requerida)
                } else if (n > 100) {
                    input.error = ctx.getString(R.string.error_cantidad_max)
                } else {
                    p.cantidad = n
                    onDone()
                    d.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    fun add(product: Product) {
        items.add(product)
        notifyItemInserted(items.lastIndex)
        onListChanged()
    }

    private fun money(n: Double): String {
        val loc = Locale("es", "PE")
        return String.format(loc, "%,.2f", n)
    }
}
