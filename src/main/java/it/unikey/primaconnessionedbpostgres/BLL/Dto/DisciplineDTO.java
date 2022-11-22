package it.unikey.primaconnessionedbpostgres.BLL.Dto;

import lombok.Data;

import java.util.List;

@Data
public class DisciplineDTO {
    private Integer id;
    private String name;
    private List<AthleteDTO> athletes;
}
