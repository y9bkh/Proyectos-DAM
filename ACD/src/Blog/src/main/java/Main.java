import Model.Post;
import Repository.PostImpl;
import Repository.PostRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {

        // URI de conexión con el servidor MongoDB
        String uri = "mongodb://localhost:27017/";

        // Establezco la conexion
        MongoClient mongoClient = MongoClients.create(uri);

        // Para poder usar POJOs
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Referencio la bbdd de la conexión establecida con MongoDB
        MongoDatabase bbdd = mongoClient.getDatabase("HeraldoAragon").withCodecRegistry(pojoCodecRegistry);

        // Creo una colección nueva
        bbdd.createCollection("postsCollection");

        // Referencio la colección
        MongoCollection<Post> postsCollection = bbdd.getCollection("postsCollection", Post.class);;

        PostRepository postRepo = new PostImpl();

        List<Post> postList = new ArrayList<>(); // Creo una lista vacía que servira para añadir mis posts y luego pasarselos al metodo

        // Crear post
        Post p1 = new Post();
        p1.setTitulo("Las drogas");
        p1.setDescripcionPost("Son peligrosas");
        p1.setFechaPublicacion(2024,12,01);
        p1.setAutor("Younes");
        p1.añadirComentario("Mar", "Me gusta tu contenido.");
        p1.setLikes(100);

        postList.add(p1); // Añadimos a la lista

        Post p2 = new Post();
        p2.setTitulo("Los Sims4");
        p2.setDescripcionPost("Precio competitivo con expansiones incluidos");
        p2.setFechaPublicacion(2024,12,01);
        p2.setAutor("Sara");
        p2.añadirComentario("Carlos", "Me encanta ese juego, lo comprare hoy.");
        p2.añadirComentario("Flora", "¡¡Espectacular!!");
        p2.setLikes(52);

        postList.add(p2);

        postRepo.escribirPost(postsCollection, postList);

        // Borrar post
        postRepo.borrarPost(postsCollection, "autor", "Sara");

        // Listar post
        postRepo.listarPost(postList);
        // Cerrar la conexión del servidor
        mongoClient.close();
    }

    }
