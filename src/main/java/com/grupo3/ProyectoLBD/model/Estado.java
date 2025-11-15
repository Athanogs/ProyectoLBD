package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_ESTADO_TB")
public class Estado {

    @Id
    @Column(name = "ID_ESTADO")
    private Long idEstado;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Estado() {
    }

    public Estado(Long idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}