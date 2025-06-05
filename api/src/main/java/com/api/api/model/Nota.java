package com.api.api.model;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título no puede estar vacío")
    @Size(min = 3, max = 100, message = "Título debe tener entre 3 y 100 caracteres")
    String titulo;
    
    @NotBlank(message = "Contenido no puede estar vacío")
    @Size(min = 5, max = 10000, message = "Contenido debe tener entre 5 y 10000 caracteres")
    String contenido;
    LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Nota() {
    }

    public Nota(Long id, String titulo, String contenido, LocalDateTime fechaCreacion, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
