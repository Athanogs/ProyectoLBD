package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvaluacionDocenteId implements Serializable {

    @Column(name = "CEDULA")
    private Long cedula;

    @Column(name = "ID_TIPO_EVALUACION")
    private Long idTipoEvaluacion;

    @Column(name = "ANIO")
    private Integer anio;

    public EvaluacionDocenteId() {
    }

    public EvaluacionDocenteId(Long cedula, Long idTipoEvaluacion, Integer anio) {
        this.cedula = cedula;
        this.idTipoEvaluacion = idTipoEvaluacion;
        this.anio = anio;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Long getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(Long idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvaluacionDocenteId)) return false;
        EvaluacionDocenteId that = (EvaluacionDocenteId) o;
        return Objects.equals(cedula, that.cedula) &&
               Objects.equals(idTipoEvaluacion, that.idTipoEvaluacion) &&
               Objects.equals(anio, that.anio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, idTipoEvaluacion, anio);
    }
}