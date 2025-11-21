package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(RegistroAsistenciaId.class)
@Table(name = "FIDE_REGISTRO_ASISTENCIA_V")
public class RegistroAsistenciaView {

    @Id
    @Column(name = "CEDULA_INFANTE")
    private Long cedulaInfante;

    @Id
    @Column(name = "FECHA_ASISTENCIA")
    private LocalDate fechaAsistencia;

    @Column(name = "NOMBRE_INFANTE")
    private String nombreInfante;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;

    @Column(name = "HORA_ENTRADA")
    private String horaEntrada;

    @Column(name = "HORA_SALIDA")
    private String horaSalida;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ESTADO_ASISTENCIA")
    private String estadoAsistencia;

    // Getters y setters
    public Long getCedulaInfante() { return cedulaInfante; }
    public void setCedulaInfante(Long cedulaInfante) { this.cedulaInfante = cedulaInfante; }

    public LocalDate getFechaAsistencia() { return fechaAsistencia; }
    public void setFechaAsistencia(LocalDate fechaAsistencia) { this.fechaAsistencia = fechaAsistencia; }

    public String getNombreInfante() { return nombreInfante; }
    public void setNombreInfante(String nombreInfante) { this.nombreInfante = nombreInfante; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(String horaEntrada) { this.horaEntrada = horaEntrada; }

    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getEstadoAsistencia() { return estadoAsistencia; }
    public void setEstadoAsistencia(String estadoAsistencia) { this.estadoAsistencia = estadoAsistencia; }
}
