package com.prueba.Recargas.service;

import com.prueba.Recargas.model.Recarga;
import com.prueba.Recargas.repository.RecargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecargaService {

    @Autowired
    private RecargaRepository recargaRepository;

    // Obtener todas las recargas
    public List<Recarga> findAll() {
        return recargaRepository.findAll();
    }

    // Obtener una recarga por ID
    public Optional<Recarga> findById(Long id) {
        return recargaRepository.findById(id);
    }

    // Crear o actualizar una recarga
    public Recarga save(Recarga recarga) {
        return recargaRepository.save(recarga);
    }

    // Eliminar una recarga por ID
    public void deleteById(Long id) {
        recargaRepository.deleteById(id);
    }
}
