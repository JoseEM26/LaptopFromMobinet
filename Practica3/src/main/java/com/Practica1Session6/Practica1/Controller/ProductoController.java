package com.Practica1Session6.Practica1.Controller;

import com.Practica1Session6.Practica1.Helpers.Alert;
import com.Practica1Session6.Practica1.Model.Producto;
import com.Practica1Session6.Practica1.Repository.CategoriaRepository;
import com.Practica1Session6.Practica1.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    CategoriaRepository _cateCategoriaRepository;
    @Autowired
    ProductoRepository _productoRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("lista",_productoRepository.findAllByOrderByIdDesc());
        return "producto/index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("producto",new Producto());
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        return "producto/create";
    }
    @PostMapping("/post")
    public String post(Model model , @ModelAttribute Producto producto , RedirectAttributes redirectAttributes){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        _productoRepository.save(producto);
        redirectAttributes.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se ingreso correctamente"));
        return "redirect:/producto/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model , @PathVariable int id){
        Producto producto=_productoRepository.findById(id).orElseThrow();
        model.addAttribute("producto",producto);
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        return "producto/edit";
    }
    @PostMapping("/editar")
    public String editar(Model model , @ModelAttribute Producto producto , RedirectAttributes redirectAttributes){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        _productoRepository.save(producto);
        redirectAttributes.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se Actualizo correctamente"));
        return "redirect:/producto/index";
    }
}
