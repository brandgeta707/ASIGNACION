package com.example.asignacion.controller;

import com.example.asignacion.model.Asignacion;
import com.example.asignacion.repository.AsignacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin("*")
public class AsignacionController {

    private final AsignacionRepository repo;

    public AsignacionController(AsignacionRepository repo) {
        this.repo = repo;
    }

    // LISTAR
    @GetMapping
    public List<Asignacion> listar() {
        return repo.findAll();
    }

    // CREAR
    @PostMapping
    public Asignacion crear(@RequestBody Asignacion asignacion) {
        return repo.save(asignacion);
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public Asignacion obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public Asignacion actualizar(@PathVariable Long id, @RequestBody Asignacion asignacion) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setDocenteId(asignacion.getDocenteId());
                    existing.setMateriaId(asignacion.getMateriaId());
                    existing.setGradoId(asignacion.getGradoId());
                    existing.setAnioLectivo(asignacion.getAnioLectivo());
                    return repo.save(existing);
                })
                .orElse(null);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
