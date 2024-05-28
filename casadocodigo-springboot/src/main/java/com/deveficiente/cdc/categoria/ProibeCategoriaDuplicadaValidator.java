package com.deveficiente.cdc.categoria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCategoriaDuplicadaValidator implements Validator {

    private CategoriaRepository categoriaRepository;

    public ProibeCategoriaDuplicadaValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCategoriaRequest request = (NovaCategoriaRequest) target;

        if (categoriaRepository.existsByNome(request.getNome())) {
            errors.rejectValue("nome", null, "Já existe uma categoria com o mesmo nome " + request.getNome());
        }
    }
}
