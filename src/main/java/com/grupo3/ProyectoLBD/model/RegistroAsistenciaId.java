package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroAsistenciaId implements Serializable {
    private Long cedulaInfante;
    private LocalDate fechaAsistencia;

    // Constructor vacío
    public RegistroAsistenciaId() {}

    public RegistroAsistenciaId(Long cedulaInfante, LocalDate fechaAsistencia) {
        this.cedulaInfante = cedulaInfante;
        this.fechaAsistencia = fechaAsistencia;
    }

    // getters y setters
    public Long getCedulaInfante() { return cedulaInfante; }
    public void setCedulaInfante(Long cedulaInfante) { this.cedulaInfante = cedulaInfante; }

    public LocalDate getFechaAsistencia() { return fechaAsistencia; }
    public void setFechaAsistencia(LocalDate fechaAsistencia) { this.fechaAsistencia = fechaAsistencia; }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroAsistenciaId)) return false;
        RegistroAsistenciaId that = (RegistroAsistenciaId) o;
        return cedulaInfante.equals(that.cedulaInfante) &&
               fechaAsistencia.equals(that.fechaAsistencia);
    }

    @Override
    public int hashCode() {
        return cedulaInfante.hashCode() + fechaAsistencia.hashCode();
    }
}