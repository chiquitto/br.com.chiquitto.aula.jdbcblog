package br.com.chiquitto.aula.jdbcblog.dao;

import br.com.chiquitto.aula.jdbcblog.Conexao;
import br.com.chiquitto.aula.jdbcblog.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcblog.exception.ValidationFailedException;
import br.com.chiquitto.aula.jdbcblog.vo.Admin;
import br.com.chiquitto.aula.jdbcblog.vo.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chiquitto
 */
public class AdminDao extends PessoaDao {

    public Admin login(Admin admin) throws ValidationFailedException, RowNotFoundException {
        int tipo = Pessoa.TIPO_ADMIN;

        try {
            String sql = "Select idpessoa, nome, email, senha From pessoa Where (tipo=?) And (email = ?) And (senha = ?)";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, tipo);
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getSenha());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa pessoa = recordset2Vo(rs, tipo);
                return (Admin) pessoa;
            }
        } catch (SQLException e) {
            throw new ValidationFailedException();
        }

        throw new RowNotFoundException();
    }
}
