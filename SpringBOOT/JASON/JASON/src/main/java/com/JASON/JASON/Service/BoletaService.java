package com.JASON.JASON.Service;

import com.JASON.JASON.Model.Persona;
import com.JASON.JASON.Repository.PersonaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class BoletaService {

    @Autowired
    private PersonaRepository personaRepository;

    public byte[] generarBoletaPdf(Long idPersona) throws JRException {
        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // Cargar el archivo .jrxml
        InputStream inputStream = getClass().getResourceAsStream("/reportes/boleta_persona.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Llenar con datos
        List<Persona> lista = Collections.singletonList(persona);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
