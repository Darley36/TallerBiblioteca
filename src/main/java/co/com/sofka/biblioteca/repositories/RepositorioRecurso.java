package co.com.sofka.biblioteca.repositories;

import co.com.sofka.biblioteca.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {
    List<Recurso> findByThematic(final String thematic);
    List<Recurso> findByType(final String type);
}
