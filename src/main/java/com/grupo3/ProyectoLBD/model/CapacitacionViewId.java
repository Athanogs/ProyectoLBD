package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.util.Objects;

public class CapacitacionViewId implements Serializable {

    private Long cedula;
    private Integer idTema;
    private Integer idProveedor;

    public CapacitacionViewId() {
    }

    public CapacitacionViewId(Long cedula, Integer idTema, Integer idProveedor) {
        this.cedula = cedula;
        this.idTema = idTema;
        this.idProveedor = idProveedor;
    }

    // getters/setters opcionales

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacitacionViewId)) return false;
        CapacitacionViewId that = (CapacitacionViewId) o;
        return Objects.equals(cedula, that.cedula)
                && Objects.equals(idTema, that.idTema)
                && Objects.equals(idProveedor, that.idProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, idTema, idProveedor);
    }
}