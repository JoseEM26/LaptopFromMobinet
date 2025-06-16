package com.Exercise1.Exercise01;

import com.Exercise1.Exercise01.Model.Producto;
import com.Exercise1.Exercise01.Repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Exercise01Application {
	@Autowired
	private ProductoRepository productoRepository;

	@PostConstruct
	void Init(){
		Producto producto= new Producto();
		producto.setEstadoProducto(1);
		producto.setId("P0031");
		producto.setPrecio(100.20);
		producto.setDescripcion("Galletita InkaChip");
		producto.setIdCategoria(4);
		producto.setIdProveedor(2);
		producto.setStock(100);

		productoRepository.save(producto);

		productoRepository.findAll().forEach(x->{
			System.out.println("Producto" + x.getId() + "-" + x.getDescripcion()+"\n");
		});
	}
	public static void main(String[] args) {
		SpringApplication.run(Exercise01Application.class, args);
	}

}
