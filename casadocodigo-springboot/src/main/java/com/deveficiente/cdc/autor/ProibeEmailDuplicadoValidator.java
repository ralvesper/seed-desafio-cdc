package com.deveficiente.cdc.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovoAutorRequest request = (NovoAutorRequest) target;

        autorRepository.findByEmail(request.getEmail())
            .ifPresent(autor -> errors.rejectValue("email", null, "Já existe um(a) autor(a) com o mesmo email " + request.getEmail()));


    }
}
