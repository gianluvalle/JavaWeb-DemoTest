package it.unikey.primaconnessionedbpostgres.PL.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AthleteRest {
    private Integer id;
    private String name;
    private String lastName;
    private Integer age;
    private Double height;
    private DisciplineRest discipline;
}
