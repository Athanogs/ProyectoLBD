package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_PROVINCIA_TB")
public class FideProvinciaTb {

    @Id
    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdProvincia() { return idProvincia; }
    public void setIdProvincia(Integer idProvincia) { this.idProvincia = idProvincia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}