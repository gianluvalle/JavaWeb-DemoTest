package it.unikey.primaconnessionedbpostgres.BLL.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDTO {

    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
//    @Min(value = 18, message = "Age must be over than 18")
    private Integer age;

    private Double height;
    private DisciplineDTO discipline;
}
