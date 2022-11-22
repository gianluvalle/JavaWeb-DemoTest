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
public class DisciplineMapperImpl implements DisciplineMapper {

    @Override
    public DisciplineDTO fromEntityToDto(DisciplineEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DisciplineDTO disciplineDTO = new DisciplineDTO();

        disciplineDTO.setId( entity.getId() );
        disciplineDTO.setName( entity.getName() );
        disciplineDTO.setAthletes( athleteEntityListToAthleteDTOList( entity.getAthletes() ) );

        return disciplineDTO;
    }

    @Override
    public DisciplineEntity fromDtoToEntity(DisciplineDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisciplineEntity disciplineEntity = new DisciplineEntity();

        disciplineEntity.setId( dto.getId() );
        disciplineEntity.setName( dto.getName() );
        disciplineEntity.setAthletes( athleteDTOListToAthleteEntityList( dto.getAthletes() ) );

        return disciplineEntity;
    }

    @Override
    public List<DisciplineDTO> fromEntityListToDtoList(List<DisciplineEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DisciplineDTO> list = new ArrayList<DisciplineDTO>( entities.size() );
        for ( DisciplineEntity disciplineEntity : entities ) {
            list.add( fromEntityToDto( disciplineEntity ) );
        }

        return list;
    }

    @Override
    public List<DisciplineEntity> fromDtoListToEntityList(List<DisciplineDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<DisciplineEntity> list = new ArrayList<DisciplineEntity>( dtos.size() );
        for ( DisciplineDTO disciplineDTO : dtos ) {
            list.add( fromDtoToEntity( disciplineDTO ) );
        }

        return list;
    }

    protected AthleteDTO athleteEntityToAthleteDTO(AthleteEntity athleteEntity) {
        if ( athleteEntity == null ) {
            return null;
        }

        AthleteDTO athleteDTO = new AthleteDTO();

        athleteDTO.setId( athleteEntity.getId() );
        athleteDTO.setName( athleteEntity.getName() );
        athleteDTO.setLastName( athleteEntity.getLastName() );
        athleteDTO.setAge( athleteEntity.getAge() );
        athleteDTO.setHeight( athleteEntity.getHeight() );
        athleteDTO.setDiscipline( fromEntityToDto( athleteEntity.getDiscipline() ) );

        return athleteDTO;
    }

    protected List<AthleteDTO> athleteEntityListToAthleteDTOList(List<AthleteEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<AthleteDTO> list1 = new ArrayList<AthleteDTO>( list.size() );
        for ( AthleteEntity athleteEntity : list ) {
            list1.add( athleteEntityToAthleteDTO( athleteEntity ) );
        }

        return list1;
    }

    protected AthleteEntity athleteDTOToAthleteEntity(AthleteDTO athleteDTO) {
        if ( athleteDTO == null ) {
            return null;
        }

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setId( athleteDTO.getId() );
        athleteEntity.setName( athleteDTO.getName() );
        athleteEntity.setLastName( athleteDTO.getLastName() );
        athleteEntity.setAge( athleteDTO.getAge() );
        athleteEntity.setHeight( athleteDTO.getHeight() );
        athleteEntity.setDiscipline( fromDtoToEntity( athleteDTO.getDiscipline() ) );

        return athleteEntity;
    }

    protected List<AthleteEntity> athleteDTOListToAthleteEntityList(List<AthleteDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<AthleteEntity> list1 = new ArrayList<AthleteEntity>( list.size() );
        for ( AthleteDTO athleteDTO : list ) {
            list1.add( athleteDTOToAthleteEntity( athleteDTO ) );
        }

        return list1;
    }
}
