package org.example;


import Model.Autor;
import Model.Categoria;
import Model.Libro;
import Model.Portada;
import Repository.LibroImpl;
import Repository.LibroRepositorio;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        LibroRepositorio lrepo = new LibroImpl(entityManager);

        // Crear y guardar un libro
        Libro l1 = new Libro();
        l1.setTitulo("Alicia en el pais de las maravillas");

        Categoria c1 = new Categoria();
        c1.setNombre("Ficción");
        entityManager.persist(c1);

        Portada p1 = new Portada();
        p1.setDescripcion("Una aventura mágica");
        p1.setURL("https://www.planetadelibros.com/usuaris/libros/fotos/265/original/portada_alicia-en-el-pais-de-las-maravillas-cuentos-de-buenas-noches_disney_201801161632.jpg");
        entityManager.persist(p1);

        Autor a1 = new Autor();
        a1.setNombre("Lewis Carroll");
        a1.setNacionalidad("Inglesa");
        entityManager.persist(a1);

        Set<Autor> autores = new HashSet<>();
        autores.add(a1);

        l1.setCategoria(c1);
        l1.setPortada(p1);
        l1.setAutores(autores);

        // Crear y guardar un libro
        Libro l2 = new Libro();
        l2.setTitulo("Tiempo entre costuras");

        Categoria c2 = new Categoria();
        c2.setNombre("Drama");
        entityManager.persist(c2);

        Portada p2 = new Portada();
        p2.setDescripcion("Costurera durante la guerra civil");
        p2.setURL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.planetadelibros.com%2Flibro-el-tiempo-entre-costuras%2F269163&psig=AOvVaw3w8hovq_PM9o-xTUYsgNb_&ust=1737058795221000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOjdxrXG-IoDFQAAAAAdAAAAABAE");
        entityManager.persist(p2);

        Autor a2 = new Autor();
        a2.setNombre("María Dueñas");
        a2.setNacionalidad("Española");
        entityManager.persist(a2);

        Set<Autor> autores2 = new HashSet<>();
        autores2.add(a2);

        l2.setCategoria(c2);
        l2.setPortada(p2);
        l2.setAutores(autores2);

        // Crear y guardar un libro
        Libro l3 = new Libro();
        l3.setTitulo("1984");

        Categoria c3 = new Categoria();
        c3.setNombre("Política de ficción");
        entityManager.persist(c3);

        Portada p3 = new Portada();
        p3.setDescripcion("Paradojas de regimenes totalitarios");
        p3.setURL("www.1984.es");
        entityManager.persist(p3);

        Autor a3 = new Autor();
        a3.setNombre("George Orwell");
        a3.setNacionalidad("Britanico");
        entityManager.persist(a3);

        Set<Autor> autores3 = new HashSet<>();
        autores3.add(a3);

        l3.setCategoria(c3);
        l3.setPortada(p3);
        l3.setAutores(autores);

        lrepo.registrarLibro(l1);
        lrepo.registrarLibro(l2);
        lrepo.registrarLibro(l3);

        // Modificar la portada de un libro
        Portada nuevaPortada = new Portada();
        nuevaPortada.setId(l1.getId());
        nuevaPortada.setURL("yahoo.es");
        nuevaPortada.setDescripcion("El buscador");

        lrepo.modificarPortada(1L, nuevaPortada);

        // Consultar libros por categoria
        lrepo.listarLibrosporCategoria("Drama");

        // Consultar los libros por autores
        //lrepo.listarAutoresporTitulo("1984");

        transaction.commit(); // Para que se realice la transaccion de datos a la base de datos (envia toda la info a la bbdd)

    }

}
