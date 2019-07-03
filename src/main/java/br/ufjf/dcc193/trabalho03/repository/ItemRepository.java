package br.ufjf.dcc193.trabalho03.repository;

import br.ufjf.dcc193.trabalho03.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trabalho03.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query("SELECT i FROM Item i WHERE :etiqueta IN (i.etiquetas)")
    List<Item> findAllByEtiqueta(@Param("etiqueta") Etiqueta etiqueta);
}