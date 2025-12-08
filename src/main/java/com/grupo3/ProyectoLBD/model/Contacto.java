package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_CONTACTOS_V")
public class Contacto {

    @Id
    @Column(name = "CEDULA")
    private String cedula;

    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;

    @Column(name = "EMAILS")
    private String emails;

    @Column(name = "TELEFONOS")
    private String telefonos;

    // GETTERS / SETTERS

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public String getTelefonos() { return telefonos; }
    public void setTelefonos(String telefonos) { this.telefonos = telefonos; }
}