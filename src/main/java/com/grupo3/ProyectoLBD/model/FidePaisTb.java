package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_PAIS_TB")
public class FidePaisTb {

    @Id
    @Column(name = "ID_PAIS")
    private Integer idPais;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdPais() { return idPais; }
    public void setIdPais(Integer idPais) { this.idPais = idPais; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}