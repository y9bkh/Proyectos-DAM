package Model;

import jakarta.persistence.*;
import lombok.*;


import java.util.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor //Introduce todos los constructores posibles
@NoArgsConstructor
public class Categoria {
    @Id // Marca el campo como la clave de la tabla
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Libro> libros = new ArrayList<Libro>();
}
