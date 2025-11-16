package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FIDE_PERSONA_USUARIO_ROL_VW")
public class PersonaUsuarioRolView {

    @Id
    @Column(name = "CEDULA")
    private Long cedula;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;   

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @Column(name = "ROL")
    private String rol;

    @Column(name = "ID_ROL")
    private Integer idRol;

    @Column(name = "ID_PAIS")
    private Integer idPais;

    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;

    @Column(name = "ID_CANTON")
    private Integer idCanton;

    @Column(name = "ID_DISTRITO")
    private Integer idDistrito;

    @Column(name = "OTRAS_SENAS")
    private String otrasSenas;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    //Getters y setters
    public Long getCedula() { return cedula; }
    public void setCedula(Long cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }

    public Integer getIdPais() { return idPais; }
    public void setIdPais(Integer idPais) { this.idPais = idPais; }

    public Integer getIdProvincia() { return idProvincia; }
    public void setIdProvincia(Integer idProvincia) { this.idProvincia = idProvincia; }

    public Integer getIdCanton() { return idCanton; }
    public void setIdCanton(Integer idCanton) { this.idCanton = idCanton; }

    public Integer getIdDistrito() { return idDistrito; }
    public void setIdDistrito(Integer idDistrito) { this.idDistrito = idDistrito; }

    public String getOtrasSenas() { return otrasSenas; }
    public void setOtrasSenas(String otrasSenas) { this.otrasSenas = otrasSenas; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}