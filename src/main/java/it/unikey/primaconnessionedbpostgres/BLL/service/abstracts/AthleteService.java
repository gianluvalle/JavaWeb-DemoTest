package it.unikey.primaconnessionedbpostgres.BLL.service.abstracts;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;

import java.util.List;

public interface AthleteService extends CrudService<AthleteDTO> {
    List<AthleteDTO> getByNameAndLastName(String name, String lastName) throws NotFoundException;
}
