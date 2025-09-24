package com.Practica1Session6.Practica1.Controller;

import com.Practica1Session6.Practica1.Model.Producto;
import com.Practica1Session6.Practica1.Repository.CategoriaRepository;
import com.Practica1Session6.Practica1.Repository.InventarioRepository;
import com.Practica1Session6.Practica1.Repository.ProductoRepository;
import com.Practica1Session6.Practica1.Utils.Alert;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    CategoriaRepository _CategoriaRepository;

    @Autowired
    InventarioRepository _InventarioRepository;

    @Autowired
    ProductoRepository _proProductoRepository;

    @GetMapping("/index")
    public String index(Model model){

        model.addAttribute("productos",_proProductoRepository.findAll());
        return "producto/index";
    }
    @GetMapping("/create")
    public String Create(Model model){
        model.addAttribute("cboCategoria",_CategoriaRepository.findAll());
        model.addAttribute("producto",new Producto());
        return "producto/create";
    }
    @PostMapping("/save")
    public String Save(@ModelAttribute Producto producto, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("cboCategoria", _CategoriaRepository.findAll());
        _proProductoRepository.save(producto);

            String alerta = Alert.sweetAlertSuccess("Se registró correctamente");


        redirectAttributes.addFlashAttribute("alert", alerta);
        return "redirect:/producto/index";
    }

    @GetMapping("/editar/{id}")
    public String Editar(Model model , @PathVariable int id){
           model.addAttribute("cboCategoria",_CategoriaRepository.findAll());
           Producto producto= _proProductoRepository.findById(id).orElseThrow();
           model.addAttribute("producto",producto);
           return "producto/editar";

    }
    @PostMapping("/edit")
    public String Edit(@ModelAttribute Producto producto, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("cboCategoria", _CategoriaRepository.findAll());
        _proProductoRepository.save(producto);

        String alerta = Alert.sweetAlertSuccess("Se Actualizo correctamente");


        redirectAttributes.addFlashAttribute("alert", alerta);
        return "redirect:/producto/index";
    }

}
