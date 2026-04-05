package com.uade.tpo.e_commerce3.model;

import jakarta.persistence.*;   
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String email;

    // Relación OneToMany: Un usuario tiene muchos productos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;   


}
    