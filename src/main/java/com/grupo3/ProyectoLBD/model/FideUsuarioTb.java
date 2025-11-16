package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_USUARIO_TB")
public class FideUsuarioTb {

    @Id
    @Column(name = "CEDULA")
    private Long cedula;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Long getCedula() { return cedula; }
    public void setCedula(Long cedula) { this.cedula = cedula; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}