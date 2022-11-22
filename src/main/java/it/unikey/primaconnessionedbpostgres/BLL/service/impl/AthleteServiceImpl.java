package it.unikey.primaconnessionedbpostgres.BLL.service.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;
import it.unikey.primaconnessionedbpostgres.BLL.mapper.impl.AthleteMapper;
import it.unikey.primaconnessionedbpostgres.BLL.service.abstracts.AthleteService;
import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import it.unikey.primaconnessionedbpostgres.DAL.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final AthleteMapper athleteMapper;

    @Override
    public AthleteDTO insert(AthleteDTO dto) {
        AthleteEntity entityToSave = athleteMapper.fromDtoToEntity(dto);
        AthleteEntity entitySaved = athleteRepository.save(entityToSave);
        return athleteMapper.fromEntityToDto(entitySaved);
//        return athleteMapper.fromAthleteEntityToAthleteDto(athleteRepository.save(athleteMapper.fromAthleteDtoToAthleteEntity(dto)));

    }

    @Override
    public AthleteDTO getById(Integer id) throws NotFoundException {
/*        if(!athleteRepository.existsById(id)){
            throw new NotFoundException("Entity not found in db");
        }
        AthleteEntity entity = athleteRepository.getById(id);
        return athleteMapper.fromAthleteEntityToAthleteDto(entity);*/
        AthleteEntity entity = athleteRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity not found in DB"));
        return athleteMapper.fromEntityToDto(entity);
    }

    @Override
    public List<AthleteDTO> getByNameAndLastName(String name, String lastName) throws NotFoundException {
        if(!athleteRepository.existsAthleteEntitiesByNameAndLastName(name, lastName))
            throw new NotFoundException("Entity not found in DB");
        return athleteMapper.fromEntityListToDtoList(athleteRepository.findAthleteEntityByNameAndLastName(name, lastName));
    }

    @Override
    public List<AthleteDTO> getAll() {
        List<AthleteEntity> entities = athleteRepository.findAll();
       // List<AthleteDTO> dtoList = entities.stream().map(athleteMapper::fromAthleteEntityToAthleteDto).collect(Collectors.toList());
        List<AthleteDTO> dtoList = entities.stream().map(athleteMapper::fromEntityToDto).collect(Collectors.toList());
        return dtoList;
      //  return athleteMapper.fromEntityListToDtoList(athleteRepository.findAll());
    }

    @Override
    public AthleteDTO update(AthleteDTO dto) throws NotFoundException {
        if(!athleteRepository.existsById(dto.getId())) {
            throw new NotFoundException("Entity not found in db");
        }
        AthleteEntity entityUpdated = athleteRepository.save(athleteMapper.fromDtoToEntity(dto));
        return athleteMapper.fromEntityToDto(entityUpdated);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!athleteRepository.existsById(id))
            throw new NotFoundException("Entity not found in db");
        athleteRepository.deleteById(id);
    }
}
