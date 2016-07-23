package br.com.chiquitto.aula.jdbcblog.dao;

import br.com.chiquitto.aula.jdbcblog.Conexao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.vo.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class PostDao extends AbstractDao {

    public void apagar(Post post) {
        try {
            String sqlDelete = "Delete From post Where (idpost = ?)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, post.getIdpost());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Post post) {
        String sql = "Insert Into post"
                + " (idcategoria, idpessoa, titulo, texto)"
                + " Values"
                + " (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, post.getCategoria().getIdcategoria());
            stmt.setInt(2, post.getAutor().getIdpessoa());
            stmt.setString(3, post.getTitulo());
            stmt.setString(4, post.getTexto());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            post.setIdpost(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Post post) {
        String sql = "Update post"
                + " Set "
                + " idcategoria = ?,"
                + " idpessoa = ?,"
                + " titulo = ?,"
                + " texto = ?"
                + " Where (idpost = ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, post.getCategoria().getIdcategoria());
            stmt.setInt(2, post.getAutor().getIdpessoa());
            stmt.setString(3, post.getTitulo());
            stmt.setString(4, post.getTexto());
            stmt.setInt(5, post.getIdpost());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        try {
            String sql = "Select idpost, idcategoria, idpessoa, titulo, texto From post";

            PreparedStatement st = Conexao.getConexao().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                posts.add(recordset2Vo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public Post getOne(int idpost) throws RowNotFoundException {
        try {
            String sql = "Select idpost, idcategoria, idpessoa, titulo, texto From post Where (idpost = ?)";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idpost);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return recordset2Vo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }

    private Post recordset2Vo(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setIdpost(rs.getInt("idpost"));
        post.setIdcategoria(rs.getInt("idcategoria"));
        post.setIdautor(rs.getInt("idpessoa"));
        post.setTitulo(rs.getString("titulo"));
        post.setTexto(rs.getString("texto"));
        return post;
    }

}
