package com.grupo3.ProyectoLBD.model;

import java.io.Serializable;
import java.util.Objects;

public class ActividadGrupo implements Serializable {

    private Integer idActividad;
    private Integer idGrupo;

    public ActividadGrupo() {}

    public ActividadGrupo(Integer idActividad, Integer idGrupo) {
        this.idActividad = idActividad;
        this.idGrupo = idGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActividadGrupo)) return false;
        ActividadGrupo id = (ActividadGrupo) o;
        return Objects.equals(idActividad, id.idActividad)
                && Objects.equals(idGrupo, id.idGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividad, idGrupo);
    }
}