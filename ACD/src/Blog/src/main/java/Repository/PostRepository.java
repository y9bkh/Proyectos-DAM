package Repository;

import Model.Post;
import com.mongodb.client.MongoCollection;

import java.util.List;

public interface PostRepository {
    //Post guardarPost(Post post);
    void listarPost(List<Post> postList);
    void escribirPost(MongoCollection<Post> posts, List<Post> postList);
    void borrarPost(MongoCollection<Post> posts, String variable, String nombre);
}
