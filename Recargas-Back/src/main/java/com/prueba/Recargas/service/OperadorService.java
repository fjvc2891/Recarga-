package com.prueba.Recargas.service;

import com.prueba.Recargas.model.Operador;
import com.prueba.Recargas.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    // Obtener todos los operadores
    public List<Operador> findAll() {
        return operadorRepository.findAll();
    }

    // Obtener un operador por ID
    public Optional<Operador> findById(Long id) {
        return operadorRepository.findById(id);
    }

    // Crear o actualizar un operador
    public Operador save(Operador operador) {
        return operadorRepository.save(operador);
    }

    // Eliminar un operador por ID
    public void deleteById(Long id) {
        operadorRepository.deleteById(id);
    }
}
