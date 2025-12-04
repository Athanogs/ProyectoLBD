package com.grupo3.ProyectoLBD.security;

import com.grupo3.ProyectoLBD.model.FideUsuarioTb;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final FideUsuarioTb usuario;

    public CustomUserDetails(FideUsuarioTb usuario) {
        this.usuario = usuario;
    }

    // 🔵 MÉTODO NECESARIO PARA ACCEDER AL USUARIO DESDE EL CONTROLADOR
    public FideUsuarioTb getUsuario() {
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Obtener el nombre del rol desde la relación: Usuario -> Persona -> Rol
        String rolNombre = usuario.getPersona().getRol().getNombre().toUpperCase();

        return List.of(new SimpleGrantedAuthority("ROLE_" + rolNombre));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}