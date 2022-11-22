package it.unikey.primaconnessionedbpostgres.BLL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import it.unikey.primaconnessionedbpostgres.DAL.entity.DisciplineEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-22T13:10:17+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class AthleteMapperImpl implements AthleteMapper {

    @Override
    public AthleteEntity fromDtoToEntity(AthleteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setId( dto.getId() );
        athleteEntity.setName( dto.getName() );
        athleteEntity.setLastName( dto.getLastName() );
        athleteEntity.setAge( dto.getAge() );
        athleteEntity.setHeight( dto.getHeight() );
        athleteEntity.setDiscipline( disciplineDTOToDisciplineEntity( dto.getDiscipline() ) );

        return athleteEntity;
    }

    @Override
    public List<AthleteEntity> fromDtoListToEntityList(List<AthleteDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<AthleteEntity> list = new ArrayList<AthleteEntity>( dtos.size() );
        for ( AthleteDTO athleteDTO : dtos ) {
            list.add( fromDtoToEntity( athleteDTO ) );
        }

        return list;
    }

    @Override
    public AthleteDTO fromEntityToDto(AthleteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AthleteDTO athleteDTO = new AthleteDTO();

        athleteDTO.setId( entity.getId() );
        athleteDTO.setName( entity.getName() );
        athleteDTO.setLastName( entity.getLastName() );
        athleteDTO.setAge( entity.getAge() );
        athleteDTO.setHeight( entity.getHeight() );
        athleteDTO.setDiscipline( disciplineEntityToDisciplineDTO( entity.getDiscipline() ) );

        return athleteDTO;
    }

    @Override
    public List<AthleteDTO> fromEntityListToDtoList(List<AthleteEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AthleteDTO> list = new ArrayList<AthleteDTO>( entities.size() );
        for ( AthleteEntity athleteEntity : entities ) {
            list.add( fromEntityToDto( athleteEntity ) );
        }

        return list;
    }

    protected DisciplineEntity disciplineDTOToDisciplineEntity(DisciplineDTO disciplineDTO) {
        if ( disciplineDTO == null ) {
            return null;
        }

        DisciplineEntity disciplineEntity = new DisciplineEntity();

        disciplineEntity.setId( disciplineDTO.getId() );
        disciplineEntity.setName( disciplineDTO.getName() );
        disciplineEntity.setAthletes( fromDtoListToEntityList( disciplineDTO.getAthletes() ) );

        return disciplineEntity;
    }

    protected DisciplineDTO disciplineEntityToDisciplineDTO(DisciplineEntity disciplineEntity) {
        if ( disciplineEntity == null ) {
            return null;
        }

        DisciplineDTO disciplineDTO = new DisciplineDTO();

        disciplineDTO.setId( disciplineEntity.getId() );
        disciplineDTO.setName( disciplineEntity.getName() );

        return disciplineDTO;
    }
}
