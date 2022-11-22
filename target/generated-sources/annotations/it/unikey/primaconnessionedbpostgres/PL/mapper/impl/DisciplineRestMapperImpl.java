package it.unikey.primaconnessionedbpostgres.PL.mapper.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.PL.rest.AthleteRest;
import it.unikey.primaconnessionedbpostgres.PL.rest.DisciplineRest;
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
public class DisciplineRestMapperImpl implements DisciplineRestMapper {

    @Override
    public DisciplineRest fromDtoToRest(DisciplineDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DisciplineRest disciplineRest = new DisciplineRest();

        disciplineRest.setId( dto.getId() );
        disciplineRest.setName( dto.getName() );
        disciplineRest.setAthletes( athleteDTOListToAthleteRestList( dto.getAthletes() ) );

        return disciplineRest;
    }

    @Override
    public DisciplineDTO fromRestToDto(DisciplineRest rest) {
        if ( rest == null ) {
            return null;
        }

        DisciplineDTO disciplineDTO = new DisciplineDTO();

        disciplineDTO.setId( rest.getId() );
        disciplineDTO.setName( rest.getName() );
        disciplineDTO.setAthletes( athleteRestListToAthleteDTOList( rest.getAthletes() ) );

        return disciplineDTO;
    }

    @Override
    public List<DisciplineRest> fromDtoListToRestList(List<DisciplineDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<DisciplineRest> list = new ArrayList<DisciplineRest>( dtos.size() );
        for ( DisciplineDTO disciplineDTO : dtos ) {
            list.add( fromDtoToRest( disciplineDTO ) );
        }

        return list;
    }

    @Override
    public List<DisciplineDTO> fromRestListToDtoList(List<DisciplineRest> rests) {
        if ( rests == null ) {
            return null;
        }

        List<DisciplineDTO> list = new ArrayList<DisciplineDTO>( rests.size() );
        for ( DisciplineRest disciplineRest : rests ) {
            list.add( fromRestToDto( disciplineRest ) );
        }

        return list;
    }

    protected AthleteRest athleteDTOToAthleteRest(AthleteDTO athleteDTO) {
        if ( athleteDTO == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String lastName = null;
        Integer age = null;
        Double height = null;
        DisciplineRest discipline = null;

        id = athleteDTO.getId();
        name = athleteDTO.getName();
        lastName = athleteDTO.getLastName();
        age = athleteDTO.getAge();
        height = athleteDTO.getHeight();
        discipline = fromDtoToRest( athleteDTO.getDiscipline() );

        AthleteRest athleteRest = new AthleteRest( id, name, lastName, age, height, discipline );

        return athleteRest;
    }

    protected List<AthleteRest> athleteDTOListToAthleteRestList(List<AthleteDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<AthleteRest> list1 = new ArrayList<AthleteRest>( list.size() );
        for ( AthleteDTO athleteDTO : list ) {
            list1.add( athleteDTOToAthleteRest( athleteDTO ) );
        }

        return list1;
    }

    protected AthleteDTO athleteRestToAthleteDTO(AthleteRest athleteRest) {
        if ( athleteRest == null ) {
            return null;
        }

        AthleteDTO athleteDTO = new AthleteDTO();

        athleteDTO.setId( athleteRest.getId() );
        athleteDTO.setName( athleteRest.getName() );
        athleteDTO.setLastName( athleteRest.getLastName() );
        athleteDTO.setAge( athleteRest.getAge() );
        athleteDTO.setHeight( athleteRest.getHeight() );
        athleteDTO.setDiscipline( fromRestToDto( athleteRest.getDiscipline() ) );

        return athleteDTO;
    }

    protected List<AthleteDTO> athleteRestListToAthleteDTOList(List<AthleteRest> list) {
        if ( list == null ) {
            return null;
        }

        List<AthleteDTO> list1 = new ArrayList<AthleteDTO>( list.size() );
        for ( AthleteRest athleteRest : list ) {
            list1.add( athleteRestToAthleteDTO( athleteRest ) );
        }

        return list1;
    }
}
