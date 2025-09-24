package com.JASON.JASON.Controller;

import com.JASON.JASON.Service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boleta")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> obtenerBoleta(@PathVariable Long id) {
        try {
            byte[] pdfBytes = boletaService.generarBoletaPdf(id);

            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("boleta_persona.pdf").build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
