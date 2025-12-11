package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_CAPACITACION_V")
@IdClass(CapacitacionViewId.class)
public class CapacitacionView {

    @Id
    @Column(name = "CEDULA")
    private Long cedula;

    @Id
    @Column(name = "ID_TEMA")
    private Integer idTema;

    @Id
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;

    @Column(name = "TEMA")
    private String tema;

    @Column(name = "PROVEEDOR")
    private String proveedor;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "HORAS")
    private Integer horas;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public CapacitacionView() {
    }

    // Getters y setters

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}