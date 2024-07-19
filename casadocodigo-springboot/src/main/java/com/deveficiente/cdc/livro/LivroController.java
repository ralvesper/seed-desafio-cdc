package com.deveficiente.cdc.livro;

import com.deveficiente.cdc.autor.Autor;
import com.deveficiente.cdc.categoria.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/livros")
    @Transactional
    public ResponseEntity<NovoLivroDTO> cadastrar(@RequestBody @Valid NovoLivroRequest request) {

        Livro livro = request.getLivro().toModel(
           id -> manager.find(Categoria.class, id),
           id -> manager.find(Autor.class, id)
        );

        manager.persist(livro);

        request.getLivro().setId(livro.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).body(request.getLivro());
    }

    @GetMapping("/livros")
    public ResponseEntity<LivrosResponse> obtertodos() {
        List<NovoLivroDTO> livros = manager.createQuery("select l from Livro l", Livro.class)
                .getResultList()
                .stream()
                .map(livro -> new NovoLivroDTO().fromModel(livro))
                .toList();

        return ResponseEntity.ok(LivrosResponse.builder().livros(livros).build());

    }

}
