package br.ufjf.dcc193.trabalho03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trabalho03.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
        Usuario findOneByEmailAndCodigoAcesso(String email, String codigoAcesso);

}