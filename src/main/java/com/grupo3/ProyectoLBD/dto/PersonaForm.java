package com.grupo3.ProyectoLBD.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonaForm {
    private String cedula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String username;
    private String contrasena;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private Integer idRol;
    private Integer idPais;
    private Integer idProvincia;
    private Integer idCanton;
    private Integer idDistrito;
    private String otrasSenas;
    private Integer idEstado;

    //getters y setters
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Integer getIdRol() {
        return idRol;
    }
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
    public Integer getIdPais() {
        return idPais;
    }
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    public Integer getIdProvincia() {
        return idProvincia;
    }
    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }
    public Integer getIdCanton() {
        return idCanton;
    }
    public void setIdCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }
    public Integer getIdDistrito() {
        return idDistrito;
    }
    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }
    public String getOtrasSenas() {
        return otrasSenas;
    }
    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }
    public Integer getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    
}
    

