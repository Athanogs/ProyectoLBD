package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "FIDE_GESTION_INFANTES_V")
public class InfanteGestionView {

    @Id
    @Column(name = "CEDULA_INFANTE")
    private String cedulaInfante;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Column(name = "CEDULA_PADRE")
    private String cedulaPadre;

    @Column(name = "NOMBRE_GRUPO")
    private String nombreGrupo;

    @Column(name = "ALERGIAS")
    private String alergias;

    @Column(name = "MEDICAMENTOS")
    private String medicamentos;

    @Column(name = "VACUNAS")
    private String vacunas;

    @Column(name = "ESTADO")
    private Integer estado;

    // GETTERS & SETTERS

    public String getCedulaInfante() { return cedulaInfante; }
    public void setCedulaInfante(String cedulaInfante) { this.cedulaInfante = cedulaInfante; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getCedulaPadre() { return cedulaPadre; }
    public void setCedulaPadre(String cedulaPadre) { this.cedulaPadre = cedulaPadre; }

    public String getNombreGrupo() { return nombreGrupo; }
    public void setNombreGrupo(String nombreGrupo) { this.nombreGrupo = nombreGrupo; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getMedicamentos() { return medicamentos; }
    public void setMedicamentos(String medicamentos) { this.medicamentos = medicamentos; }

    public String getVacunas() { return vacunas; }
    public void setVacunas(String vacunas) { this.vacunas = vacunas; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}