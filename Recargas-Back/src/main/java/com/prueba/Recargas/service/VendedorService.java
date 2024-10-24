package com.prueba.Recargas.service;

import com.prueba.Recargas.model.Vendedor;
import com.prueba.Recargas.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Obtener todos los vendedores
    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    // Obtener un vendedor por ID
    public Optional<Vendedor> findById(Long id) {
        return vendedorRepository.findById(id);
    }

    // Crear o actualizar un vendedor
    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Eliminar un vendedor por ID
    public void deleteById(Long id) {
        vendedorRepository.deleteById(id);
    }
}
