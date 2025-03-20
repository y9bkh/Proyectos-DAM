package Repository;

import Model.Post;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Indexes.ascending;

public class PostImpl implements PostRepository {
    /*
    @Override
    public Post guardarPost(Post post) {
        System.out.println("Guardando post: " + post);
        return post; // Simula guardar el post y devolverlo
    }
    */

    @Override
    public void listarPost(List<Post> postList) {
        System.out.println("Mostrando todos los posts...");
        for(int p = 0; p<postList.size(); p++){
            System.out.println(postList);
        }
    }

    @Override
    public void escribirPost(MongoCollection<Post> posts, List<Post> postList) {
        posts.insertMany(postList);
    }


    @Override
    public void borrarPost(MongoCollection<Post> posts, String variable, String nombre) {
        Bson filter = Filters.eq(variable, nombre);
        posts.deleteMany(filter);
    }
}
