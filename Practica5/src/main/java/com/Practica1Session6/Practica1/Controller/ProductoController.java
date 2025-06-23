package com.Practica1Session6.Practica1.Controller;

import com.Practica1Session6.Practica1.Model.Producto;
import com.Practica1Session6.Practica1.Repository.CategoriaRepository;
import com.Practica1Session6.Practica1.Repository.ProductoRepository;
import com.Practica1Session6.Practica1.helpers.Alert;
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
    ProductoRepository _prodProductoRepository;

    @GetMapping("/index")
    public String listar(Model model){
        model.addAttribute("lista",_prodProductoRepository.findAllByOrderByIdDesc());
        return "producto/index";
    }
    @GetMapping("/create")
    public String create(Model model ){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        model.addAttribute("producto",new Producto());
        return "producto/create";
    }
    @PostMapping("/save")
    public String save(Model model , @ModelAttribute Producto productonew, RedirectAttributes redirectAttributes){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        _prodProductoRepository.save(productonew);
        String mensaje= Alert.sweetAlertSuccess("Se registro correctamente");
        redirectAttributes.addFlashAttribute("alert",mensaje);
        return "redirect:/producto/index";
    }
    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        Producto producto=_prodProductoRepository.findById(id).orElseThrow();
        model.addAttribute("producto",producto);
        return "producto/editar";
    }
    @PostMapping("/edit")
    public String edit(Model model , @ModelAttribute Producto productonew, RedirectAttributes redirectAttributes){
        model.addAttribute("cbo",_cateCategoriaRepository.findAll());
        _prodProductoRepository.save(productonew);
        String mensaje= Alert.sweetAlertSuccess("Se Actualizo correctamente");
        redirectAttributes.addFlashAttribute("alert",mensaje);
        return "redirect:/producto/index";
    }


}
