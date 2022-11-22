package it.unikey.primaconnessionedbpostgres.PL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.PL.mapper.abstracts.GenericRestMapper;
import it.unikey.primaconnessionedbpostgres.PL.rest.AthleteRest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AthleteRestMapper extends GenericRestMapper<AthleteDTO, AthleteRest> {
    @Mapping(ignore = true, target = "discipline.athletes")
    @Override
    AthleteRest fromDtoToRest(AthleteDTO dto);

    @Mapping(ignore = true, target = "discipline.athletes")
    @Override
    List<AthleteRest> fromDtoListToRestList(List<AthleteDTO> dtos);
}
