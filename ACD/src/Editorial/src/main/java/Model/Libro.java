package Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id // Marca el campo como la clave de la tabla
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String titulo;

    //Relacion uno a uno con Portada
    @OneToOne
    @JoinColumn(name = "fk_portada")
    private Portada portada;

    //Relacion mucho a uno con Categoria
    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

    //Relacion mucho a muchos con Autor
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<Autor>();
}
