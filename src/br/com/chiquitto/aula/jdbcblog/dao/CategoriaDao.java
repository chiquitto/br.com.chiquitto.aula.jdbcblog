package br.com.chiquitto.aula.jdbcblog.dao;

import br.com.chiquitto.aula.jdbcblog.Conexao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.vo.Categoria;
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
public class CategoriaDao extends AbstractDao {

    public void apagar(Categoria categoria) {
        try {
            String sqlDelete = "Delete From categoria Where (idcategoria = ?)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, categoria.getIdcategoria());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Categoria categoria) {
        String sql = "Insert Into categoria"
                + " (categoria)"
                + " Values"
                + " (?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, categoria.getCategoria());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            categoria.setIdcategoria(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Categoria categoria) {
        String sql = "Update categoria"
                + " Set "
                + " categoria = ?"
                + " Where (idcategoria = ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, categoria.getCategoria());
            stmt.setInt(2, categoria.getIdcategoria());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            String sql = "Select idcategoria, categoria From categoria";

            PreparedStatement st = Conexao.getConexao().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                categorias.add(recordset2Vo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public Categoria getOne(int idcategoria) throws RowNotFoundException {
        try {
            String sql = "Select idcategoria, categoria From categoria Where (idcategoria = ?)";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idcategoria);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return recordset2Vo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }

    private Categoria recordset2Vo(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdcategoria(rs.getInt("idcategoria"));
        categoria.setCategoria(rs.getString("categoria"));
        return categoria;
    }

}
