package br.ufjf.dcc193.trabalho03.repository;

import br.ufjf.dcc193.trabalho03.model.Item;
import br.ufjf.dcc193.trabalho03.model.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Long>  {
    List<Vinculo> findAllByItemDestinoOrItemOrigem(Item origem, Item destino);
}
