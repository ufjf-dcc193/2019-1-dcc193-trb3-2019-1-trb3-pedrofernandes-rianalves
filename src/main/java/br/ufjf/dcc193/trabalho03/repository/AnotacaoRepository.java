package br.ufjf.dcc193.trabalho03.repository;

import br.ufjf.dcc193.trabalho03.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}
