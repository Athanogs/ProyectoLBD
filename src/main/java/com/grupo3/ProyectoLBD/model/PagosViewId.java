package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.util.Objects;

public class PagosViewId implements Serializable {
    private Integer idPago;
    private Integer idFactura;

    public PagosViewId() {}

    public PagosViewId(Integer idPago, Integer idFactura) {
        this.idPago = idPago;
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago, idFactura);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PagosViewId other = (PagosViewId) obj;
        return Objects.equals(idPago, other.idPago) && Objects.equals(idFactura, other.idFactura);
    }
    
}
