package br.ufjf.dcc193.trabalho03.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Item itemOrigem;
    @ManyToOne
    private Item itemDestino;
    @ManyToMany
    List<Etiqueta> etiquetas;
    @OneToMany
    List<Anotacao> anotacoes; 

    public Long getId() {
        return id;
    }
   
    public Item getItemOrigem() {
        return itemOrigem;
    }
 
    public Item getItemDestino() {
        return itemDestino;
    }
 
    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }
    
  
    public void setItemOrigem(Item itemOrigem) {
        this.itemOrigem = itemOrigem;
    }

    public void setItemDestino(Item itemDestino) {
        this.itemDestino = itemDestino;
    }
  
    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
  
    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
}