package com.Practica1Session6.Practica1.Controller;

import com.Practica1Session6.Practica1.Model.Categoria;
import com.Practica1Session6.Practica1.Model.Producto;
import com.Practica1Session6.Practica1.Repository.CategoriaRepository;
import com.Practica1Session6.Practica1.Repository.ProductoRepository;
import com.Practica1Session6.Practica1.Utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoRepository _proProductoRepository;
    @Autowired
    CategoriaRepository _cateCategoriaRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("lista",_proProductoRepository.findAllByOrderByIdDesc());
        return "producto/index";
    }

    @GetMapping("/create")
    public String create(Model model ){
        model.addAttribute("producto",new Producto());
        model.addAttribute("cbo", _cateCategoriaRepository.findAll());
        return "producto/create";
    }
    @PostMapping("/save")
    public String save(Model model , @ModelAttribute Producto producto , RedirectAttributes redirectAttributes){
        model.addAttribute("cbo", _cateCategoriaRepository.findAll());
        Producto newProducto=_proProductoRepository.save(producto);
        String mensaje= Alert.sweetAlertSuccess("Se registro correctamente el producto "+newProducto.getNombre());
        redirectAttributes.addFlashAttribute("alert",mensaje);
        return "redirect:/producto/index";
    }
    @GetMapping("/editar/{id}")
    public String editar(Model model ,@PathVariable int id){
        Producto producto= _proProductoRepository.findById(id).orElseThrow();
        model.addAttribute("producto",producto);
        model.addAttribute("cbo", _cateCategoriaRepository.findAll());
        return "producto/editar";
    }
    @PostMapping("/edit")
    public String edit(Model model , @ModelAttribute Producto producto , RedirectAttributes redirectAttributes){
        model.addAttribute("cbo", _cateCategoriaRepository.findAll());
        Producto newProducto=_proProductoRepository.save(producto);
        String mensaje= Alert.sweetAlertSuccess("Se EDITO correctamente el producto "+newProducto.getNombre());
        redirectAttributes.addFlashAttribute("alert",mensaje);
        return "redirect:/producto/index";
    }


}
