package co.com.sofka.biblioteca.services;

import co.com.sofka.biblioteca.dtos.RecursoDTO;
import co.com.sofka.biblioteca.mappers.RecursoMapper;
import co.com.sofka.biblioteca.models.Recurso;
import co.com.sofka.biblioteca.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioRecurso {

    @Autowired
    RepositorioRecurso repositorioRecurso;
    RecursoMapper mapper = new RecursoMapper();

    public List<RecursoDTO> obtenerTodos() {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll();
        return mapper.fromCollectionList(recursos);
    }
    public RecursoDTO obtenerPorId(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return mapper.fromCollection(recurso);
    }
    public RecursoDTO crear(RecursoDTO recursoDTO) {
        if (sonVacios(recursoDTO)) {
            throw new IllegalArgumentException("Los datos no pueden estar vacios");
        }
        Recurso recurso = mapper.fromDTO(recursoDTO);
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    private boolean sonVacios(RecursoDTO recursoDTO){
        if (recursoDTO.getName().isEmpty() || recursoDTO.getThematic().isEmpty() || recursoDTO.getType().isEmpty()) {
            return false;
        }
        return true;
    }
    public RecursoDTO modificar(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        repositorioRecurso.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    public void borrar(String id) {
        repositorioRecurso.deleteById(id);
    }

    public String availavility(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        if (recurso.isAvailable()) {
            return "El recurso " + recurso.getName() + "se encuentra disponible";
        }
        return "El recurso " + recurso.getName() + "no se encuentra disponible";
    }

    public String loanApplication(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        if (!recurso.isAvailable()) {
            recurso.setAvailable(true);
            recurso.setDate(LocalDate.now());
            RecursoDTO recursoDTO = mapper.fromCollection(recurso);
            modificar(recursoDTO);
            return "El recurso " + recursoDTO.getName() + " se ha prestado";
        }
        return "el recurso " + recurso.getName() + " no tiene derechos para prestamo";
    }

    public String returnRequest(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        if (recurso.isAvailable()) {
            recurso.setAvailable(false);
            LocalDate date = LocalDate.of(9999, 12, 31);
            recurso.setDate(date);
            RecursoDTO recursoDTO = mapper.fromCollection(recurso);
            modificar(recursoDTO);
            return "El recurso " + recursoDTO.getName() + " se ha prestado";
        }
        return "el recurso " + recurso.getName() + " no tiene derechos para prestamo";
    }
}
