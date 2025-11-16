package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_DIRECCION_TB")
public class FideDireccionTb {

    @EmbeddedId
    private FideDireccionId id;

    @Column(name = "OTRAS_SENAS")
    private String otrasSenas;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public FideDireccionId getId() { return id; }
    public void setId(FideDireccionId id) { this.id = id; }

    public String getOtrasSenas() { return otrasSenas; }
    public void setOtrasSenas(String otrasSenas) { this.otrasSenas = otrasSenas; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}