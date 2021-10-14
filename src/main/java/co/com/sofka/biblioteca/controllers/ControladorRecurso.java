package co.com.sofka.biblioteca.controllers;


import co.com.sofka.biblioteca.dtos.RecursoDTO;
import co.com.sofka.biblioteca.services.ServicioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
@RequestMapping("/recursos")
public class ControladorRecurso {

    @Autowired
    ServicioRecurso servicioRecurso;

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id) {
        try {
            return new ResponseEntity(servicioRecurso.obtenerPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity("Recurso no esta",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity(servicioRecurso.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO) {
        if (recursoDTO.getId() != null) {
            return new ResponseEntity(servicioRecurso.modificar(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioRecurso.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity("Recurso no esta",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disponible/{id}")
    public ResponseEntity findAvailable(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.availavility(id), HttpStatus.OK);
    }
/*
    @GetMapping("/disponible/{id}")
    public ResponseEntity loanApplication(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.availavility(id), HttpStatus.OK);
    }
*/


}
