package com.example.hospitalmsystem.controller;


import com.example.hospitalmsystem.model.Department;
import com.example.hospitalmsystem.service.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServices service;
    public DepartmentController(DepartmentServices service) { this.service = service; }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Department> create(@Valid @RequestBody Department d) {
        return ResponseEntity.ok(service.createDepartment(d));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Department> all() { return service.findAllDepartment(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Department one(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Department update(@PathVariable Long id, @Valid @RequestBody Department d) {
        return service.updateDepartment(id, d);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
