package br.com.chiquitto.aula.jdbcblog.dao;

import br.com.chiquitto.aula.jdbcblog.Conexao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.vo.Admin;
import br.com.chiquitto.aula.jdbcblog.vo.Leitor;
import br.com.chiquitto.aula.jdbcblog.vo.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
abstract public class PessoaDao extends AbstractDao {

    public void apagar(Pessoa pessoa) {
        try {
            String sqlDelete = "Delete From pessoa Where (idpessoa = ?) And (tipo = ?)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, pessoa.getIdpessoa());
            stmtDelete.setInt(2, pessoa.getTipo());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Pessoa pessoa) {
        String sql = "Insert Into pessoa"
                + " (tipo, nome, email, senha)"
                + " Values"
                + " (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pessoa.getTipo());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getSenha());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            pessoa.setIdpessoa(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Pessoa pessoa) {
        String sql = "Update pessoa"
                + " Set "
                + " nome = ?,"
                + " email = ?,"
                + " senha = ?"
                + " Where (idpessoa = ?)"
                + "     And (tipo = ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setInt(4, pessoa.getIdpessoa());
            stmt.setInt(5, pessoa.getTipo());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Pessoa> getAll() {
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            String sql = "Select idpessoa, nome, email, senha From pessoa Where tipo=?";

            int tipo = 0;

            if (this instanceof AdminDao) {
                tipo = Pessoa.TIPO_ADMIN;
            } else if (this instanceof LeitorDao) {
                tipo = Pessoa.TIPO_LEITOR;
            }
            
            PreparedStatement st = Conexao.getConexao().prepareStatement(sql);
            st.setInt(1, tipo);
            
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = recordset2Vo(rs, tipo);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public Pessoa getOne(int idpessoa) throws RowNotFoundException {
        int tipo = 0;

        if (this instanceof AdminDao) {
            tipo = Pessoa.TIPO_ADMIN;
        } else if (this instanceof LeitorDao) {
            tipo = Pessoa.TIPO_LEITOR;
        }

        try {
            String sql = "Select idpessoa, nome, email, senha From pessoa Where (tipo=?) And (idpessoa = ?)";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, tipo);
            stmt.setInt(2, idpessoa);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa pessoa = recordset2Vo(rs, tipo);
                return pessoa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }

    protected Pessoa recordset2Vo(ResultSet rs, int tipo) throws SQLException {
        Pessoa pessoa;

        switch (tipo) {
            case Pessoa.TIPO_ADMIN:
                pessoa = new Admin();
                break;

            case Pessoa.TIPO_LEITOR:
                pessoa = new Leitor();
                break;

            default:
                return null;
        }

        pessoa.setIdpessoa(rs.getInt("idpessoa"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setEmail(rs.getString("email"));
        pessoa.setSenha(rs.getString("senha"));

        return pessoa;
    }

}
