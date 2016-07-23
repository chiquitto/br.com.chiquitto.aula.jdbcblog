package br.com.chiquitto.aula.jdbcblog.vo;

/**
 *
 * @author chiquitto
 */
abstract public class Pessoa extends AbstractVo {

    private int idpessoa;
    private int tipo;
    private String nome;
    private String email;
    private String senha;

    public static final int TIPO_ADMIN = 1;
    public static final int TIPO_LEITOR = 2;

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getTipo() {
        return tipo;
    }

    protected void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return idpessoa + ":" + nome;
    }

}
