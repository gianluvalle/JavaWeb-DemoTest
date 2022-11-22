package it.unikey.primaconnessionedbpostgres.PL.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class DisciplineRest {
    private Integer id;
    private String name;
    @JsonIgnore
    private List<AthleteRest> athletes;
}
