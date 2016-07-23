package br.com.chiquitto.aula.jdbcblog;

import br.com.chiquitto.aula.jdbcblog.dao.PostDao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.vo.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chiquitto
 */
public class Test {
    
    private static int idadmin1 = 1;
    private static int idadmin2 = 2;
    private static int idleitor1 = 3;
    private static int idleitor2 = 4;
    private static int idcategoriaEsportes = 1;
    private static int idcategoriaPolitica = 2;
    private static int idcategoriaGames = 3;
    private static int idpostFutebol = 1;
    private static int idpostVoley = 2;
    private static int idpostGames = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao.setUrl("jdbc:sqlite:/Users/chiquitto/work/aula/br.com.chiquitto.aula.jdbcblog/data/blog.sqlite.db");

        post();
    }

    private static void post() {
        PostDao postDao = new PostDao();
        Post post;
        
        // Cadastrar post
        post = new Post();
        post.setIdcategoria(idcategoriaEsportes);
        post.setIdautor(idadmin1);
        post.setTitulo("Titulo cadastrar");
        post.setTexto("Cadastrar Cadastrar Cadastrar");
        postDao.cadastrar(post);
        System.out.println("Post cadastrado:" + post.getIdpost());
        
        try {
            post = postDao.getOne(post.getIdpost());
            System.out.println(post);
        } catch (RowNotFoundException ex) {
            System.out.println("Post " + post.getIdpost() + " inexistente");
        }
        
        // Editar post
        post = new Post();
        post.setIdcategoria(idcategoriaGames);
        post.setIdautor(idadmin2);
        post.setTitulo("Titulo cadastrar");
        post.setTexto("Texto Texto Texto");
        postDao.cadastrar(post);
        post.setTitulo("Titulo editar");
        post.setTexto("Editar Editar Editar");
        postDao.editar(post);
        System.out.println("Post editado:" + post.getIdpost());
        
        // Apagar post
        post = new Post();
        post.setIdcategoria(idcategoriaGames);
        post.setIdautor(idadmin2);
        post.setTitulo("Titulo apagar");
        post.setTexto("");
        postDao.cadastrar(post);
        postDao.apagar(post);
        
        try {
            post = postDao.getOne(post.getIdpost());
            System.out.println(post);
        } catch (RowNotFoundException ex) {
            System.out.println("Post " + post.getIdpost() + " inexistente");
        }
        
        // getall
        List<Post> posts = postDao.getAll();
        for (Post postItem : posts) {
            System.out.println(postItem);
        }
    }

}
