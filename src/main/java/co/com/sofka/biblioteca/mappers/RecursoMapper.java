package co.com.sofka.biblioteca.mappers;

import co.com.sofka.biblioteca.dtos.RecursoDTO;
import co.com.sofka.biblioteca.models.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setName(dto.getName());
        recurso.setDate(dto.getDate());
        recurso.setAvailable(dto.isAvailable());
        recurso.setThematic(dto.getThematic());
        recurso.setQuantityAvailable(dto.getQuantityAvailable());
        recurso.setQuantityBorrowed(dto.getQuantityBorrowed());

        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setName(collection.getName());
        recursoDTO.setDate(collection.getDate());
        recursoDTO.setAvailable(collection.isAvailable());
        recursoDTO.setThematic(collection.getThematic());
        recursoDTO.setQuantityAvailable(collection.getQuantityAvailable());
        recursoDTO.setQuantityBorrowed(collection.getQuantityBorrowed());

        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;

        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso empleado = (Recurso) listTracks.next();
            list.add(fromCollection(empleado));
        }

        return list;
    }
}
