package br.com.chiquitto.aula.jdbcblog;

import br.com.chiquitto.aula.jdbcblog.dao.AdminDao;
import br.com.chiquitto.aula.jdbcblog.dao.CategoriaDao;
import br.com.chiquitto.aula.jdbcblog.dao.LeitorDao;
import br.com.chiquitto.aula.jdbcblog.dao.PostDao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.vo.Admin;
import br.com.chiquitto.aula.jdbcblog.vo.Categoria;
import br.com.chiquitto.aula.jdbcblog.vo.Leitor;
import br.com.chiquitto.aula.jdbcblog.vo.Pessoa;
import br.com.chiquitto.aula.jdbcblog.vo.Post;
import java.util.List;

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

        categoria();
        // post();
        //admin();
        //leitor();
    }
    
    private static void admin() {
        AdminDao dao = new AdminDao();
        Admin admin;
        
        // Cadastrar post
        admin = new Admin();
        admin.setNome("Admin cadastrar");
        admin.setEmail("Email cadastrar");
        admin.setSenha("cadastrar");
        dao.cadastrar(admin);
        System.out.println("Admin cadastrado:" + admin.getIdpessoa());
        
        try {
            admin = (Admin) dao.getOne(admin.getIdpessoa());
            System.out.println(admin);
        } catch (RowNotFoundException ex) {
            System.out.println("Admin " + admin.getIdpessoa() + " inexistente");
        }
        
        // Editar post
        admin = new Admin();
        admin.setNome("Admin cadastrar");
        admin.setEmail("Email cadastrar");
        admin.setSenha("cadastrar");
        dao.cadastrar(admin);
        admin.setNome("Admin editar");
        admin.setEmail("Email editar");
        admin.setSenha("editar");
        dao.editar(admin);
        System.out.println("Admin editado:" + admin.getIdpessoa());
        
        try {
            admin = (Admin) dao.getOne(admin.getIdpessoa());
            System.out.println(admin);
        } catch (RowNotFoundException ex) {
            System.out.println("Admin " + admin.getIdpessoa() + " inexistente");
        }
        
        // Apagar post
        admin = new Admin();
        admin.setNome("Admin apagar");
        admin.setEmail("Email apagar");
        admin.setSenha("apagar");
        dao.cadastrar(admin);
        dao.apagar(admin);
        
        // getall
        List<Pessoa> pessoas = dao.getAll();
        for (Pessoa pessoaItem : pessoas) {
            System.out.println(pessoaItem);
        }
    }
    
    private static void categoria() {
        CategoriaDao dao = new CategoriaDao();
        Categoria categoria;
        
        // Cadastrar post
        categoria = new Categoria();
        categoria.setCategoria("Categoria cadastrar");
        dao.cadastrar(categoria);
        System.out.println("Categoria cadastrado:" + categoria.getIdcategoria());
        
        try {
            categoria = dao.getOne(categoria.getIdcategoria());
            System.out.println(categoria);
        } catch (RowNotFoundException ex) {
            System.out.println("Categoria " + categoria.getIdcategoria() + " inexistente");
        }
        
        // Editar post
        categoria = new Categoria();
        categoria.setCategoria("Categoria cadastrar");
        dao.cadastrar(categoria);
        categoria.setCategoria("Categoria editar");
        dao.editar(categoria);
        System.out.println("Admin editado:" + categoria.getIdcategoria());
        
        try {
            categoria = dao.getOne(categoria.getIdcategoria());
            System.out.println(categoria);
        } catch (RowNotFoundException ex) {
            System.out.println("Categoria " + categoria.getIdcategoria() + " inexistente");
        }
        
        // Apagar post
        categoria = new Categoria();
        categoria.setCategoria("Categoria apagar");
        dao.cadastrar(categoria);
        dao.apagar(categoria);
        
        // getall
        List<Categoria> categorias = dao.getAll();
        for (Categoria item : categorias) {
            System.out.println(item);
        }
    }
    
    private static void leitor() {
        LeitorDao dao = new LeitorDao();
        Leitor leitor;
        
        // Cadastrar post
        leitor = new Leitor();
        leitor.setNome("Leitor cadastrar");
        leitor.setEmail("Email cadastrar");
        leitor.setSenha("cadastrar");
        dao.cadastrar(leitor);
        System.out.println("Leitor cadastrado:" + leitor.getIdpessoa());
        
        try {
            leitor = (Leitor) dao.getOne(leitor.getIdpessoa());
            System.out.println(leitor);
        } catch (RowNotFoundException ex) {
            System.out.println("Leitor " + leitor.getIdpessoa() + " inexistente");
        }
        
        // Editar post
        leitor = new Leitor();
        leitor.setNome("Leitor cadastrar");
        leitor.setEmail("Email cadastrar");
        leitor.setSenha("cadastrar");
        dao.cadastrar(leitor);
        leitor.setNome("Leitor editar");
        leitor.setEmail("Email editar");
        leitor.setSenha("editar");
        dao.editar(leitor);
        System.out.println("Leitor editado:" + leitor.getIdpessoa());
        
        try {
            leitor = (Leitor) dao.getOne(leitor.getIdpessoa());
            System.out.println(leitor);
        } catch (RowNotFoundException ex) {
            System.out.println("Leitor " + leitor.getIdpessoa() + " inexistente");
        }
        
        // Apagar post
        leitor = new Leitor();
        leitor.setNome("Leitor apagar");
        leitor.setEmail("Email apagar");
        leitor.setSenha("apagar");
        dao.cadastrar(leitor);
        dao.apagar(leitor);
        
        // getall
        List<Pessoa> pessoas = dao.getAll();
        for (Pessoa pessoaItem : pessoas) {
            System.out.println(pessoaItem);
        }
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
        
        try {
            post = postDao.getOne(post.getIdpost());
            System.out.println(post);
        } catch (RowNotFoundException ex) {
            System.out.println("Post " + post.getIdpost() + " inexistente");
        }
        
        // Apagar post
        post = new Post();
        post.setIdcategoria(idcategoriaGames);
        post.setIdautor(idadmin2);
        post.setTitulo("Titulo apagar");
        post.setTexto("");
        postDao.cadastrar(post);
        postDao.apagar(post);
        
        // getall
        List<Post> posts = postDao.getAll();
        for (Post postItem : posts) {
            System.out.println(postItem);
        }
    }

}
