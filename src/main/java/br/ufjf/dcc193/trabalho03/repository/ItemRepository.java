package br.ufjf.dcc193.trabalho03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trabalho03.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    
}