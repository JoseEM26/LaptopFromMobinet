package com.sistema.gpon.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    // Generar reporte usando solo el idRegistro
    public byte[] generarReportParametrs(String reportName, int idRegistro) throws JRException, SQLException {
        try {
            // Cargar el archivo .jrxml
            InputStream inputStream = this.getClass().getResourceAsStream("/templates/Reports/" + reportName + ".jrxml");

            if (inputStream == null) {
                System.err.println("⚠️ No se encontró el archivo JRXML en la ruta: /templates/Reports/" + reportName + ".jrxml");
                return null;
            }

            // Compilar el .jrxml a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // Crear el mapa de parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", idRegistro); // este es el único parámetro

            // Llenar el reporte con parámetros y conexión
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

            // Exportar a PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            System.err.println("❌ Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



}
