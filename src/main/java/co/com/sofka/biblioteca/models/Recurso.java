package co.com.sofka.biblioteca.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document
public class Recurso {

    @Id
    private String id;
    private String name;
    private boolean isAvailable;
    private LocalDate date;
    private String type;
    private String thematic;
    private int quantityAvailable;
    private int quantityBorrowed;
}
