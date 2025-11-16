package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_ESTADO_TB")
public class FideEstadoTb {

    @Id
    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}