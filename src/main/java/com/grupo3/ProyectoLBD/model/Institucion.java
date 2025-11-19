package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_INSTITUCION_TB")
public class Institucion {

    @Id
    @Column(name = "ID_INSTITUCION")
    private Long idInstitucion;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_CATEGORIA")
    private String idCategoria;

    @Column(name = "ID_ESTADO")
    private String idEstado;

    // Getters y setters
    public Long getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Long idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }
}