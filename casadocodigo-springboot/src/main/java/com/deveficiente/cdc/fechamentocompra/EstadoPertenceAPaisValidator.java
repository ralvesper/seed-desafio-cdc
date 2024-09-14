package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;
        if (!estadoPertenceAPais(request.getIdEstado(), request.getIdPais())) {
            errors.rejectValue("idEstado", null, "Este estado não pertence ao país informado");
        }
    }

    private boolean estadoPertenceAPais(Long idEstado, Long idPais) {
        if (idEstado == null) {
            return true;
        }

        Estado estado = manager.find(Estado.class, idEstado);
        Pais pais = manager.find(Pais.class, idPais);

        return estado.pertenceAPais(pais);
    }
}
