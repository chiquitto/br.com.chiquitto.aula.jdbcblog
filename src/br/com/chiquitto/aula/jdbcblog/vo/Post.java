package br.com.chiquitto.aula.jdbcblog.vo;

/**
 *
 * @author chiquitto
 */
public class Post extends AbstractVo {

    private int idpost;
    private Admin autor;
    private Categoria categoria;
    private String titulo;
    private String texto;

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public Admin getAutor() {
        return autor;
    }

    public void setAutor(Admin autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setIdcategoria(int idcategoria) {
        Categoria categoria = new Categoria();
        categoria.setIdcategoria(idcategoria);
        this.setCategoria(categoria);
    }

    public void setIdautor(int idpessoa) {
        Admin autor = new Admin();
        autor.setIdpessoa(idpessoa);
        this.setAutor(autor);
    }

    @Override
    public String toString() {
        return idpost + ":" + titulo;
    }
    
}
