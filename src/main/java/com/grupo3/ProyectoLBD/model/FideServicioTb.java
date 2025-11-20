package com.grupo3.ProyectoLBD.model;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_SERVICIO_TB")
public class FideServicioTb {
    @Id
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "MONTO")
    private BigDecimal monto;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}
