package br.ufjf.dcc193.trabalho03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String codigoAcesso;
    private String descricao;
    private String email; 

    public Long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
   
    public String getCodigoAcesso() {
        return codigoAcesso;
    }
  
    public String getDescricao() {
        return descricao;
    }

  
    public String getEmail() {
        return email;
    }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public void setCodigoAcesso(String codigoAcesso) {
       this.codigoAcesso = codigoAcesso;
   }

   public void setDescricao(String descricao) {
       this.descricao = descricao;
   }
  
   public void setEmail(String email) {
       this.email = email;
   }

     
    
}