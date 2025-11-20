package com.grupo3.ProyectoLBD.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(DetalleFacturaViewId.class)
@Table(name = "FIDE_DETALLE_FACTURA_V")
public class DetalleFacturaView {
    @Id
    @Column(name = "ID_FACTURA")
    private Integer idFactura;

    @Id
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @Column(name = "SERVICIO")
    private String servicio;

    @Column(name = "MONTO_PAGADO")
    private double montoPagado;
    
    @Column(name = "ESTADO")
    private String estado;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
