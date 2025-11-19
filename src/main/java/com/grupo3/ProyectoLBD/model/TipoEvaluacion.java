package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_TIPO_EVALUACION_TB")
public class TipoEvaluacion {

    @Id
    @Column(name = "ID_TIPO_EVALUACION")
    private Long idTipoEvaluacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ID_ESTADO")
    private Long idEstado;

    public TipoEvaluacion() {
    }

    public Long getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(Long idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    
}