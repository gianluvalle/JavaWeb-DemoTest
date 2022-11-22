package it.unikey.primaconnessionedbpostgres.BLL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.BLL.mapper.abstracts.GenericMapper;
import it.unikey.primaconnessionedbpostgres.DAL.entity.DisciplineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplineMapper extends GenericMapper<DisciplineEntity, DisciplineDTO> {
}
