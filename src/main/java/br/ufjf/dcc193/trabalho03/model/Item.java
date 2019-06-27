package br.ufjf.dcc193.trabalho03.model;

import java.util.List;

import javax.persistence.OneToMany;

public class Item {

    private Long id;
    private String titulo;

    @OneToMany
    List<Anotacao> anotacoes;

    @OneToMany
    List<Etiqueta> etiquetas;

    @OneToMany
    List<Vinculo> vinculos; 


public Long getId() {
    return id;
}
public String getTitulo() {
    return titulo;
}

public List<Anotacao> getAnotacoes() {
    return anotacoes;
}

public List<Etiqueta> getEtiquetas() {
    return etiquetas;
}

public List<Vinculo> getVinculos() {
    return vinculos;
}
    
public void setTitulo(String titulo) {
    this.titulo = titulo;
}

public void setAnotacoes(List<Anotacao> anotacoes) {
    this.anotacoes = anotacoes;
}

public void setEtiquetas(List<Etiqueta> etiquetas) {
    this.etiquetas = etiquetas;
}

public void setVinculos(List<Vinculo> vinculos) {
    this.vinculos = vinculos;
}
}