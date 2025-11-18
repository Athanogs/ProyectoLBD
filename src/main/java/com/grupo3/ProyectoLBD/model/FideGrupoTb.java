package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_GRUPO_TB")
public class FideGrupoTb{

    @Id
    @Column(name = "ID_GRUPO", nullable = false)
    private Integer idGrupo;

    @Column(name = "CEDULA")
    private Long cedula; 

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    // Constructores
    public FideGrupoTb() {
    }

    public FideGrupoTb(Integer idGrupo, String nombre) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
    }

    public FideGrupoTb(Integer idGrupo, Long cedula, String nombre, Integer idEstado) {
        this.idGrupo = idGrupo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.idEstado = idEstado;
    }

    // Getters y Setters

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    // ==========================
    // ToString
    // ==========================
    @Override
    public String toString() {
        return "FideGrupoTb{" +
                "idGrupo=" + idGrupo +
                ", cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", idEstado=" + idEstado +
                '}';
    }
}