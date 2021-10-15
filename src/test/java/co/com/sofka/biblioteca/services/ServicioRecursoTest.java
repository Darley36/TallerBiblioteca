package co.com.sofka.biblioteca.services;

import co.com.sofka.biblioteca.dtos.RecursoDTO;
import co.com.sofka.biblioteca.mappers.RecursoMapper;
import co.com.sofka.biblioteca.models.Recurso;
import co.com.sofka.biblioteca.repositories.RepositorioRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioRecursoTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private ServicioRecurso servicioRecurso;


    private RecursoMapper mapper = new RecursoMapper();

    @Test
    @DisplayName("Test findAll Success")
    public void obtenerTodos() {
        var recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setAvailable(true);
        recurso1.setDate(LocalDate.EPOCH);
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);
        var recurso2 = new Recurso();
        recurso2.setId("2");
        recurso2.setAvailable(true);
        recurso2.setDate(LocalDate.EPOCH);
        recurso2.setType("revista");
        recurso2.setThematic("Comedia");
        recurso2.setName("Happy");
        recurso2.setQuantityBorrowed(1);
        recurso2.setQuantityAvailable(1);
        var lista = new ArrayList<Recurso>();
        lista.add(recurso1);
        lista.add(recurso2);
        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);

        var resultado = servicioRecurso.obtenerTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(recurso1.getName(), resultado.get(0).getName());
        Assertions.assertEquals(recurso2.getName(), resultado.get(1).getName());
    }

    @Test
    @DisplayName("Test save Success")
    public void crear() {

        var recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setAvailable(true);
        recurso1.setDate(LocalDate.EPOCH);
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        var recurso2 = new RecursoDTO();
        recurso2.setId("1");
        recurso2.setAvailable(true);
        recurso2.setDate(LocalDate.EPOCH);
        recurso2.setType("libro");
        recurso2.setThematic("Terror");
        recurso2.setName("The black");
        recurso2.setQuantityBorrowed(1);
        recurso2.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(recurso1);

        var resultado = servicioRecurso.crear(recurso2);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");

        Assertions.assertEquals(recurso1.getName(), resultado.getName(), "el nombre no corresponde");
        Assertions.assertEquals(recurso1.getType(), resultado.getType(), "el tipo no corresponde");
    }

    @Test
    @DisplayName("Test find resource by id")
    public void obtenerPorId() {

        var recurso1 = new Recurso();
        recurso1.setId("1");
        recurso1.setAvailable(true);
        recurso1.setDate(LocalDate.EPOCH);
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.findById(any())).thenReturn(java.util.Optional.of(recurso1));
        var resultado = servicioRecurso.obtenerPorId("1");
        Assertions.assertEquals(recurso1.getName(), resultado.getName(), "el nombre no corresponde");
    }



    @Test
    @DisplayName("Test find resource by id")
    public void modificar() {

        var recurso1 = new RecursoDTO();
        recurso1.setId("1");
        recurso1.setAvailable(true);
        recurso1.setDate(LocalDate.parse("2021-01-01"));
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

       var recurso2 = new Recurso();
       recurso2.setId("1");
       recurso2.setAvailable(true);
       recurso2.setDate(LocalDate.parse("2021-01-01"));
       recurso2.setType("libro");
       recurso2.setThematic("Terror");
       recurso2.setName("The black");
       recurso2.setQuantityBorrowed(1);
       recurso2.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(mapper.fromDTO(recurso1));
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(java.util.Optional.of(recurso2));
        var resultado = servicioRecurso.modificar(recurso1);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");
        Assertions.assertEquals("1", resultado.getId(), "el nombre no corresponde");
       Assertions.assertEquals(true, resultado.isAvailable(), "el tipo no corresponde");
       Assertions.assertEquals(LocalDate.parse("2021-01-01"), resultado.getDate(), "el nombre no corresponde");
       Assertions.assertEquals("libro", resultado.getType(), "el tipo no corresponde");
       Assertions.assertEquals("The black", resultado.getName(), "el nombre no corresponde");
       Assertions.assertEquals("Terror", resultado.getThematic(), "el tipo no corresponde");
       Assertions.assertEquals(1, resultado.getQuantityAvailable(), "el nombre no corresponde");
       Assertions.assertEquals(1, resultado.getQuantityBorrowed(), "el tipo no corresponde");
    }

    @Test
    @DisplayName("Test find resource by id")
    public void modificar2() {

        var recurso1 = new RecursoDTO();
        recurso1.setId("1");
        recurso1.setAvailable(true);
        recurso1.setDate(LocalDate.parse("2021-01-01"));
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(mapper.fromDTO(recurso1));
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursos().stream().findFirst());
        var resultado = servicioRecurso.modificar(recurso1);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");
        Assertions.assertEquals("1", resultado.getId(), "el nombre no corresponde");
        Assertions.assertEquals(true, resultado.isAvailable(), "el tipo no corresponde");
        Assertions.assertEquals(LocalDate.parse("2021-01-01"), resultado.getDate(), "el nombre no corresponde");
        Assertions.assertEquals("libro", resultado.getType(), "el tipo no corresponde");
        Assertions.assertEquals("The black", resultado.getName(), "el nombre no corresponde");
        Assertions.assertEquals("Terror", resultado.getThematic(), "el tipo no corresponde");
        Assertions.assertEquals(1, resultado.getQuantityAvailable(), "el nombre no corresponde");
        Assertions.assertEquals(1, resultado.getQuantityBorrowed(), "el tipo no corresponde");
    }

    @Test
    @DisplayName("loan Application")
    public void aplicarPrestamo() {
        var recurso1 = new RecursoDTO();
        recurso1.setId("1");
        recurso1.setAvailable(false);
        recurso1.setDate(LocalDate.parse("2021-01-01"));
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(mapper.fromDTO(recurso1));
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursos().stream().findFirst());
        var resultado = servicioRecurso.loanApplication(recurso1.getId());

        Assertions.assertEquals("El recurso " + recurso1.getName() + " se ha prestado", resultado);
    }

    @Test
    @DisplayName("checkear disponibilidad")
    public void availability() {
        var recurso1 = new RecursoDTO();
        recurso1.setId("1");
        recurso1.setAvailable(false);
        recurso1.setDate(LocalDate.parse("2021-01-01"));
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(mapper.fromDTO(recurso1));
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursos().stream().findFirst());
        var resultado = servicioRecurso.availability(recurso1.getId());

        Assertions.assertEquals("El recurso " + recurso1.getName() + " se encuentra disponible", resultado);
    }

    @Test
    @DisplayName("solicitud de disponibilidad")
    public void returnRequest() {
        var recurso1 = new RecursoDTO();
        recurso1.setId("1");
        recurso1.setAvailable(false);
        recurso1.setDate(LocalDate.parse("2021-01-01"));
        recurso1.setType("libro");
        recurso1.setThematic("Terror");
        recurso1.setName("The black");
        recurso1.setQuantityBorrowed(1);
        recurso1.setQuantityAvailable(1);

        Mockito.when(repositorioRecurso.save(any())).thenReturn(mapper.fromDTO(recurso1));
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursos().stream().findFirst());
        var resultado = servicioRecurso.returnRequest(recurso1.getId());

        Assertions.assertEquals("El recurso " + recurso1.getName() + " no se pudo devolver", resultado);
    }


        private List<Recurso> recursos() {

        var recursos = new ArrayList<Recurso>();
        var recurso2 = new Recurso();
        recurso2.setId("1");
        recurso2.setAvailable(true);
        recurso2.setDate(LocalDate.EPOCH);
        recurso2.setType("libro");
        recurso2.setThematic("Terror");
        recurso2.setName("The black");
        recurso2.setQuantityBorrowed(1);
        recurso2.setQuantityAvailable(1);
        var recurso3 = new Recurso();
        recurso3.setId("3");
        recurso3.setAvailable(true);
        recurso3.setDate(LocalDate.EPOCH);
        recurso3.setType("libro");
        recurso3.setThematic("Terror");
        recurso3.setName("The black");
        recurso3.setQuantityBorrowed(1);
        recurso3.setQuantityAvailable(1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        return recursos;
    }
}

