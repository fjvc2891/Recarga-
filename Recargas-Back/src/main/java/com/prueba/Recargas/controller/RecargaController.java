package com.prueba.Recargas.controller;

import com.prueba.Recargas.model.Recarga;
import com.prueba.Recargas.service.RecargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recargas")
@CrossOrigin("http://localhost:4200/")
public class RecargaController {

    @Autowired
    private RecargaService recargaService;

    // Obtener todas las recargas
    @GetMapping
    public List<Recarga> getAllRecargas() {
        return recargaService.findAll();
    }

    // Obtener una recarga por ID
    @GetMapping("/{id}")
    public ResponseEntity<Recarga> getRecargaById(@PathVariable Long id) {
        Optional<Recarga> recarga = recargaService.findById(id);
        return recarga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva recarga
    @PostMapping
    public Recarga createRecarga(@RequestBody Recarga recarga) {
        return recargaService.save(recarga);
    }

    // Actualizar una recarga existente
    @PutMapping("/{id}")
    public ResponseEntity<Recarga> updateRecarga(@PathVariable Long id, @RequestBody Recarga recargaDetails) {
        Optional<Recarga> recarga = recargaService.findById(id);
        if (recarga.isPresent()) {
            Recarga existingRecarga = recarga.get();
            existingRecarga.setCantidad(recargaDetails.getCantidad());
            existingRecarga.setValor(recargaDetails.getValor());
            existingRecarga.setOperador(recargaDetails.getOperador());
            existingRecarga.setVendedor(recargaDetails.getVendedor());
            return ResponseEntity.ok(recargaService.save(existingRecarga));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una recarga por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecarga(@PathVariable Long id) {
        Optional<Recarga> recarga = recargaService.findById(id);
        if (recarga.isPresent()) {
            recargaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
