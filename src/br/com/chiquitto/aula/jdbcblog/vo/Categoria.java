package br.com.chiquitto.aula.jdbcblog.vo;

import java.io.Serializable;

/**
 *
 * @author chiquitto
 */
public class Categoria implements Serializable {

    private int idcategoria;
    private String categoria;

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return idcategoria + ":" + categoria;
    }
    
}
