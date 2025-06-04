package com.api.api.model;
import com.api.api.model.Nota;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    
    @OneToMany(mappedBy = "Usuario",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )

    @JsonIgnore
    private List<Nota> nota = new ArrayList<>();
    
    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String passwordHash, List<Nota> nota) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nota = nota;
    }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getNombre() {
         return nombre;
     }

     public void setNombre(String nombre) {
         this.nombre = nombre;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getPasswordHash() {
         return passwordHash;
     }

     public void setPasswordHash(String passwordHash) {
         this.passwordHash = passwordHash;
     }

     public List<Nota> getNota() {
         return nota;
     }

     public void setNota(List<Nota> nota) {
         this.nota = nota;
     }
}
