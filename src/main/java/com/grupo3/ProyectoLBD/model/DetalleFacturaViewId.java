package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.util.Objects;

public class DetalleFacturaViewId implements Serializable {

    private Long idFactura;
    private Integer idServicio;

    public DetalleFacturaViewId() {}

    public DetalleFacturaViewId(Long idFactura, Integer idServicio) {
        this.idFactura = idFactura;
        this.idServicio = idServicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, idServicio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DetalleFacturaViewId other = (DetalleFacturaViewId) obj;
        return Objects.equals(idFactura, other.idFactura) && Objects.equals(idServicio, other.idServicio);
    }
}
