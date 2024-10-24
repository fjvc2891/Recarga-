package com.prueba.Recargas.controller;

import com.prueba.Recargas.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.prueba.Recargas.model.Vendedor;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Obtener todos los vendedores
    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.findAll();
    }

    // Obtener un vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        return vendedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo vendedor
    @PostMapping
    public Vendedor createVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.save(vendedor);
    }

    // Actualizar un vendedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorDetails) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        if (vendedor.isPresent()) {
            Vendedor existingVendedor = vendedor.get();
            existingVendedor.setNombre(vendedorDetails.getNombre());
            return ResponseEntity.ok(vendedorService.save(existingVendedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        if (vendedor.isPresent()) {
            vendedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
