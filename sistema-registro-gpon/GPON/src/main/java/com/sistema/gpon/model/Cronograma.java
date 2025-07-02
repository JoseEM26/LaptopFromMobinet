    package com.sistema.gpon.model;


    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Cronograma {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "IdCronograma")
        private Integer idCronograma;

        @Column(name = "UbicacionInstalacion")
        private String ubicacionInstalacion;

        @Column(name = "RangoInstalacion")
        private String rangoInstalacion;

        @Column(name = "FechaRegistro")
        private LocalDate  fechaRegistro ;

        @Column(name = "FechaInstalacion")
        private LocalDate  fechaInstalacion ;

        @PrePersist
        public void setFechasPorDefecto() {
            if (fechaRegistro == null) {
                fechaRegistro = LocalDate.now();
            }
            if (fechaInstalacion == null) {
                fechaInstalacion = fechaRegistro.plusDays(4);
            }
        }


    }
