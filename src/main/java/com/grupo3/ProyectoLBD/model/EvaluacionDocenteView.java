package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FIDE_EVALUACION_DOCENTE_V")
public class EvaluacionDocenteView {

    @EmbeddedId
    private EvaluacionDocenteId id;

    @Column(name = "EVALUACION")
    private String evaluacion; 

    @Column(name = "CALIFICACION")
    private BigDecimal calificacion;

    @Column(name = "COMENTARIOS")
    private String comentarios;

    @Column(name = "ID_ESTADO")
    private Long idEstado;

    public EvaluacionDocenteView() {
    }

    public EvaluacionDocenteId getId() {
        return id;
    }

    public void setId(EvaluacionDocenteId id) {
        this.id = id;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public Long getCedula() {
        return (id != null) ? id.getCedula() : null;
    }

    public Integer getAnio() {
        return (id != null) ? id.getAnio() : null;
    }

    public Long getIdTipoEvaluacion() {
        return (id != null) ? id.getIdTipoEvaluacion() : null;
    }
}