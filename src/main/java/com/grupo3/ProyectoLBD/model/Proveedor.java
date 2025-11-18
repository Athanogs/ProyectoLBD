package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_PROVEEDOR_TB")
public class Proveedor {

    @Id
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ID_ESTADO")
    private Integer idEstado; // por si luego quieres filtrar solo activos

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
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