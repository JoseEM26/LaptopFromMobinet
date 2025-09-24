package com.Practica1Session6.Practica1.Controller;

import com.Practica1Session6.Practica1.Model.Producto;
import com.Practica1Session6.Practica1.Repository.CategoriaRepository;
import com.Practica1Session6.Practica1.Repository.ProductoRepository;
import com.Practica1Session6.Practica1.Repository.ProveedorRepository;
import com.Practica1Session6.Practica1.Utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    CategoriaRepository _categoriaRepository;

    @Autowired
    ProveedorRepository _ProveedorRepository;

    @Autowired
    ProductoRepository _productoRepository;

    @GetMapping("/listar")
    public String Listar(Model model){
       model.addAttribute("listProductos",_productoRepository.findAllByOrderByCodProductoDesc());
       return "productos/listar";
    }

    @GetMapping("/create")
    public String Create(Model model){
        model.addAttribute("cboCategoria" , _categoriaRepository.findAll());
        model.addAttribute("cboProveedores" , _ProveedorRepository.findAll());
        model.addAttribute("producto" ,new Producto());
        return "productos/create";
    }
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Producto producto, Model model , RedirectAttributes redirectAttributes){
        model.addAttribute("cboCategoria" , _categoriaRepository.findAll());
        model.addAttribute("cboProveedores" , _ProveedorRepository.findAll());

        Producto registrado= _productoRepository.save(producto);
        String mensaje= Alert.sweetAlertSuccess("Se registro correctamente");
        redirectAttributes.addFlashAttribute("alert",mensaje);

        return "redirect:/productos/listar";
    }
    @GetMapping("/edicion/{id}")
    public String Edicion(Model model ,@PathVariable String id){
        model.addAttribute("cboCategoria" , _categoriaRepository.findAll());
        model.addAttribute("cboProveedores" , _ProveedorRepository.findAll());
        Producto producto= _productoRepository.findById(id).orElseThrow();
        model.addAttribute("producto" ,producto);
        return "productos/edicion";
    }
    @PostMapping("/editar")
    public String Editar (@ModelAttribute Producto producto, Model model , RedirectAttributes redirectAttributes){
        model.addAttribute("cboCategoria" , _categoriaRepository.findAll());
        model.addAttribute("cboProveedores" , _ProveedorRepository.findAll());

        Producto registrado= _productoRepository.save(producto);
        String mensaje= Alert.sweetAlertSuccess("Se Actualizo correctamente");
        redirectAttributes.addFlashAttribute("alert",mensaje);

        return "redirect:/productos/listar";
    }
}
