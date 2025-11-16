package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FideDireccionId implements Serializable {

    private Long cedula;
    private Integer idPais;
    private Integer idProvincia;
    private Integer idCanton;
    private Integer idDistrito;

    public Long getCedula() { return cedula; }
    public void setCedula(Long cedula) { this.cedula = cedula; }

    public Integer getIdPais() { return idPais; }
    public void setIdPais(Integer idPais) { this.idPais = idPais; }

    public Integer getIdProvincia() { return idProvincia; }
    public void setIdProvincia(Integer idProvincia) { this.idProvincia = idProvincia; }

    public Integer getIdCanton() { return idCanton; }
    public void setIdCanton(Integer idCanton) { this.idCanton = idCanton; }

    public Integer getIdDistrito() { return idDistrito; }
    public void setIdDistrito(Integer idDistrito) { this.idDistrito = idDistrito; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FideDireccionId)) return false;
        FideDireccionId that = (FideDireccionId) o;
        return Objects.equals(cedula, that.cedula) &&
                Objects.equals(idPais, that.idPais) &&
                Objects.equals(idProvincia, that.idProvincia) &&
                Objects.equals(idCanton, that.idCanton) &&
                Objects.equals(idDistrito, that.idDistrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, idPais, idProvincia, idCanton, idDistrito);
    }
}