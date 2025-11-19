package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.util.Objects;

public class ExperienciaLaboralViewId implements Serializable {

    private Long cedula;
    private Integer idEmpresa;
    private Integer idCargo;

    public ExperienciaLaboralViewId() {
    }

    public ExperienciaLaboralViewId(Long cedula, Integer idEmpresa, Integer idCargo) {
        this.cedula = cedula;
        this.idEmpresa = idEmpresa;
        this.idCargo = idCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienciaLaboralViewId)) return false;
        ExperienciaLaboralViewId that = (ExperienciaLaboralViewId) o;
        return Objects.equals(cedula, that.cedula)
                && Objects.equals(idEmpresa, that.idEmpresa)
                && Objects.equals(idCargo, that.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, idEmpresa, idCargo);
    }
}