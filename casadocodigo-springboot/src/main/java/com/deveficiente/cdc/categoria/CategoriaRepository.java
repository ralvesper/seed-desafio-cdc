package com.deveficiente.cdc.categoria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    boolean existsByNome(String nome);

    Categoria findByNome(String nome);

}
