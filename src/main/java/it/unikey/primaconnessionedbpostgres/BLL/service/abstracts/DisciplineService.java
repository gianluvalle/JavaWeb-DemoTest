package it.unikey.primaconnessionedbpostgres.BLL.service.abstracts;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;

import java.util.List;

public interface DisciplineService extends CrudService<DisciplineDTO> {
    List<DisciplineDTO> getByName(String name) throws NotFoundException;

}
