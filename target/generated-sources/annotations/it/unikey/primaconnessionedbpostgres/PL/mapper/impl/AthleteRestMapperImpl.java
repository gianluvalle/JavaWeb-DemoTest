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
public class AthleteRestMapperImpl implements AthleteRestMapper {

    @Override
    public AthleteDTO fromRestToDto(AthleteRest rest) {
        if ( rest == null ) {
            return null;
        }

        AthleteDTO athleteDTO = new AthleteDTO();

        athleteDTO.setId( rest.getId() );
        athleteDTO.setName( rest.getName() );
        athleteDTO.setLastName( rest.getLastName() );
        athleteDTO.setAge( rest.getAge() );
        athleteDTO.setHeight( rest.getHeight() );
        athleteDTO.setDiscipline( disciplineRestToDisciplineDTO( rest.getDiscipline() ) );

        return athleteDTO;
    }

    @Override
    public List<AthleteDTO> fromRestListToDtoList(List<AthleteRest> rests) {
        if ( rests == null ) {
            return null;
        }

        List<AthleteDTO> list = new ArrayList<AthleteDTO>( rests.size() );
        for ( AthleteRest athleteRest : rests ) {
            list.add( fromRestToDto( athleteRest ) );
        }

        return list;
    }

    @Override
    public AthleteRest fromDtoToRest(AthleteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String lastName = null;
        Integer age = null;
        Double height = null;
        DisciplineRest discipline = null;

        id = dto.getId();
        name = dto.getName();
        lastName = dto.getLastName();
        age = dto.getAge();
        height = dto.getHeight();
        discipline = disciplineDTOToDisciplineRest( dto.getDiscipline() );

        AthleteRest athleteRest = new AthleteRest( id, name, lastName, age, height, discipline );

        return athleteRest;
    }

    @Override
    public List<AthleteRest> fromDtoListToRestList(List<AthleteDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<AthleteRest> list = new ArrayList<AthleteRest>( dtos.size() );
        for ( AthleteDTO athleteDTO : dtos ) {
            list.add( fromDtoToRest( athleteDTO ) );
        }

        return list;
    }

    protected DisciplineDTO disciplineRestToDisciplineDTO(DisciplineRest disciplineRest) {
        if ( disciplineRest == null ) {
            return null;
        }

        DisciplineDTO disciplineDTO = new DisciplineDTO();

        disciplineDTO.setId( disciplineRest.getId() );
        disciplineDTO.setName( disciplineRest.getName() );
        disciplineDTO.setAthletes( fromRestListToDtoList( disciplineRest.getAthletes() ) );

        return disciplineDTO;
    }

    protected DisciplineRest disciplineDTOToDisciplineRest(DisciplineDTO disciplineDTO) {
        if ( disciplineDTO == null ) {
            return null;
        }

        DisciplineRest disciplineRest = new DisciplineRest();

        disciplineRest.setId( disciplineDTO.getId() );
        disciplineRest.setName( disciplineDTO.getName() );

        return disciplineRest;
    }
}
