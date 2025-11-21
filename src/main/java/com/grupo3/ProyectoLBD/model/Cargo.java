package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_CARGO_TB")
public class Cargo {

    @Id
    @Column(name = "ID_CARGO")
    private Integer idCargo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    //Getters y setters
    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}