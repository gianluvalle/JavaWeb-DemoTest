package it.unikey.primaconnessionedbpostgres.PL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.PL.mapper.abstracts.GenericRestMapper;
import it.unikey.primaconnessionedbpostgres.PL.rest.DisciplineRest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplineRestMapper extends GenericRestMapper<DisciplineDTO, DisciplineRest> {
}
