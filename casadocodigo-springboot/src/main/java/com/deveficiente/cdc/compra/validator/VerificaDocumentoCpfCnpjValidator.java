package com.deveficiente.cdc.compra.validator;

import com.deveficiente.cdc.compra.dto.NovaCompraRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificaDocumentoCpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;
        if (!request.documentoValido()) {
            errors.rejectValue("documento", null, "Documento precisa ser um CPF ou CNPJ válido");
        }

    }
}
