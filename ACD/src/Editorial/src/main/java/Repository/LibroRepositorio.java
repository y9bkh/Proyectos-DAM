package Repository;

import Model.Autor;
import Model.Libro;
import Model.Portada;

import java.util.List;
import java.util.Set;

public interface LibroRepositorio {

    Libro registrarLibro(Libro l);

    void modificarPortada(Long id, Portada nuevaPortada);

    List<Libro> listarLibrosporCategoria(String nombreCategoria);

    List<Autor> listarAutoresporTitulo(String nombreLibro);
}
