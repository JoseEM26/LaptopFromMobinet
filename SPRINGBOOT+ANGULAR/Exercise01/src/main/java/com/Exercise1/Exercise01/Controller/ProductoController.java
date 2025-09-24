package com.Exercise1.Exercise01.Controller;

import com.Exercise1.Exercise01.Model.Producto;
import com.Exercise1.Exercise01.Repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    @GetMapping("/")
    public String Index(){
        return "Index";
    }
}
