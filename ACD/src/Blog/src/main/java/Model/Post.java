package Model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Post {

    private ObjectId id;

    private String titulo;
    private String descripcionPost; // mensaje
    private LocalDate fechaPublicacion;
    private String autor;
    private List<String> comentarios;
    private int likes;

    // Constructor vacío
    public Post() {}

    // Constructor con todos los campos
    public Post(String titulo, String descripcionPost, LocalDate fechaPublicacion, String autor, int likes) {
        this.titulo = titulo;
        this.descripcionPost = descripcionPost;
        this.fechaPublicacion = fechaPublicacion;
        this.autor = autor;
        this.comentarios = new ArrayList<>();
        this.likes = likes;
    }

    public void añadirComentario(String usuario, String texto) {
        if (this.comentarios == null) {
            this.comentarios = new ArrayList<>();
        }
        this.comentarios.add(autor + ": " + texto);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionPost() {
        return descripcionPost;
    }

    public void setDescripcionPost(String descripcionPost) {
        this.descripcionPost = descripcionPost;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int año, int mes, int dia) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void darMegusta(){
        this. likes = likes + 1;;
    }
}
