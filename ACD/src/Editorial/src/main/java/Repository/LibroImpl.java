package Repository;

import Model.Autor;
import Model.Libro;
import Model.Portada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LibroImpl implements LibroRepositorio {
    private EntityManager em;

    public LibroImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Libro registrarLibro(Libro l) {
        if (l.getId() == null) {
            em.persist(l);
        } else {
            l = em.merge(l);
        }
        return l;
    }

    @Override
    public void modificarPortada(Long libroId, Portada nuevaPortada) {
        Libro libro = em.find(Libro.class, libroId);

        // Actualiza los valores de la portada existente
        libro.getPortada().setDescripcion(nuevaPortada.getDescripcion());
        libro.getPortada().setURL(nuevaPortada.getURL());
        
        libro.setPortada(nuevaPortada);

        em.merge(libro);
    }

    @Override
    public List<Libro> listarLibrosporCategoria(String nombreCategoria) {
        List<Libro> consultaLibrosALL =
                em.createQuery("SELECT l FROM Libro l WHERE l.categoria.nombre LIKE :nombreCategoria", Libro.class)
                        .setParameter("nombreCategoria", "%" + nombreCategoria + "%")
                        .getResultList();

        for (Libro libro : consultaLibrosALL) {
            System.out.println(libro);
        }
        return consultaLibrosALL;
    }

    public List<Autor> listarAutoresporTitulo(String nombreLibro) {
         Libro libro =
                em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombreLibro", Libro.class)
                .setParameter("nombreLibro", "%" + nombreLibro + "%")
                .getSingleResult(); // Suponiendo que hay solo un libro con ese título

        List<Autor> listarAutores = new ArrayList<>(libro.getAutores());

        for (Autor autor : listarAutores) {
            System.out.println(listarAutores);
        }

        return listarAutores;
        }
}