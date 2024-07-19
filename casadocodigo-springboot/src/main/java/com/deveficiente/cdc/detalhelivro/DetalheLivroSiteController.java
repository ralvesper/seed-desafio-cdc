package com.deveficiente.cdc.detalhelivro;

import com.deveficiente.cdc.livro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheLivroSiteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public DetalheSiteLivroResponse detalheLivro(@PathVariable("id") Long id) {

        Livro livro = manager.find(Livro.class, id);



        return null;
    }
}
