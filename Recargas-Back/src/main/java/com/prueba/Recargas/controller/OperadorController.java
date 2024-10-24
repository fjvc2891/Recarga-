package com.prueba.Recargas.controller;

import com.prueba.Recargas.model.Operador;
import com.prueba.Recargas.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/operadores")
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    // Obtener todos los operadores
    @GetMapping
    public List<Operador> getAllOperadores() {
        return operadorService.findAll();
    }

    // Obtener un operador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Operador> getOperadorById(@PathVariable Long id) {
        Optional<Operador> operador = operadorService.findById(id);
        return operador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo operador
    @PostMapping
    public Operador createOperador(@RequestBody Operador operador) {
        return operadorService.save(operador);
    }

    // Actualizar un operador existente
    @PutMapping("/{id}")
    public ResponseEntity<Operador> updateOperador(@PathVariable Long id, @RequestBody Operador operadorDetails) {
        Optional<Operador> operador = operadorService.findById(id);
        if (operador.isPresent()) {
            Operador existingOperador = operador.get();
            existingOperador.setNombre(operadorDetails.getNombre());
            return ResponseEntity.ok(operadorService.save(existingOperador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un operador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperador(@PathVariable Long id) {
        Optional<Operador> operador = operadorService.findById(id);
        if (operador.isPresent()) {
            operadorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
