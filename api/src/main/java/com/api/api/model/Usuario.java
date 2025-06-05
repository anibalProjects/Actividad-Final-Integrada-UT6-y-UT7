package com.api.api.model;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    
    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(min = 3, max = 50, message = "Nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @Email(message = "Email debe ser valido")
    @Column(unique = true)
    private String email;
    private String passwordHash;
    
    @OneToMany(mappedBy = "usuario",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
    @JsonManagedReference
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
