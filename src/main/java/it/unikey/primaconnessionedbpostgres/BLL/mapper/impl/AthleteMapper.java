package it.unikey.primaconnessionedbpostgres.BLL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.mapper.abstracts.GenericMapper;
import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AthleteMapper extends GenericMapper<AthleteEntity, AthleteDTO> {
    @Mapping(ignore = true, target = "discipline.athletes")
    @Override
    AthleteDTO fromEntityToDto(AthleteEntity entity);

    @Mapping(ignore = true, target = "discipline.athletes")
    @Override
    List<AthleteDTO> fromEntityListToDtoList(List<AthleteEntity> entities);
}
