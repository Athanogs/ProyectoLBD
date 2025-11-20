package com.grupo3.ProyectoLBD.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class AsistenciaForm {

    private Long cedulaInfante;
    private String nombreInfante;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAsistencia;

    private String horaEntrada;
    private String horaSalida;
    private String observaciones;
    private String estadoAsistencia;
    private Integer idEstado;

    // Getters y Setters
    public Long getCedulaInfante() { return cedulaInfante; }
    public void setCedulaInfante(Long cedulaInfante) { this.cedulaInfante = cedulaInfante; }

    public String getNombreInfante() { return nombreInfante; }
    public void setNombreInfante(String nombreInfante) { this.nombreInfante = nombreInfante; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDate getFechaAsistencia() { return fechaAsistencia; }
    public void setFechaAsistencia(LocalDate fechaAsistencia) { this.fechaAsistencia = fechaAsistencia; }

    public String getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(String horaEntrada) { this.horaEntrada = horaEntrada; }

    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getEstadoAsistencia() { return estadoAsistencia; }
    public void setEstadoAsistencia(String estadoAsistencia) { this.estadoAsistencia = estadoAsistencia; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }

}