package Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id // Marca el campo como la clave de la tabla
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String nombre;
    private String nacionalidad;

    @ManyToMany(mappedBy="autores")
    private Set<Libro> libro = new HashSet<Libro>();
}
