package com.deveficiente.cdc.detalhelivro;

import com.deveficiente.cdc.livro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheLivroSiteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/livros/{id}")
    public ResponseEntity<DetalheSiteLivroResponse> detalhe(@PathVariable("id") Long id) {

        Livro livroBuscado = manager.find(Livro.class, id);

        if (livroBuscado == null) {
            return ResponseEntity.notFound().build();
        }

        DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(livroBuscado);
        return ResponseEntity.ok(detalheSiteLivroResponse);
    }
}
