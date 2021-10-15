package co.com.sofka.biblioteca.services;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicioRecursoTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private ServicioRecurso servicioRecurso;

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
}