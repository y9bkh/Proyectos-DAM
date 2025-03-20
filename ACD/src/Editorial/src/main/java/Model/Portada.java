package Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portada {
    @Id // Marca el campo como la clave de la tabla
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String descripcion;
    private String URL;

    @OneToOne(mappedBy = "portada")
    private Libro libro;
}
