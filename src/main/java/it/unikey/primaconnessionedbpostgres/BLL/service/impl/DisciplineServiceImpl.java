package it.unikey.primaconnessionedbpostgres.BLL.service.impl;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.DisciplineDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;
import it.unikey.primaconnessionedbpostgres.BLL.mapper.impl.DisciplineMapper;
import it.unikey.primaconnessionedbpostgres.BLL.service.abstracts.DisciplineService;
import it.unikey.primaconnessionedbpostgres.DAL.entity.DisciplineEntity;
import it.unikey.primaconnessionedbpostgres.DAL.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;

    @Override
    public DisciplineDTO insert(DisciplineDTO dto) {
        return disciplineMapper.fromEntityToDto(disciplineRepository.save(disciplineMapper.fromDtoToEntity(dto)));
    }

    @Override
    public DisciplineDTO getById(Integer id) throws NotFoundException {
        DisciplineEntity entity = disciplineRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity not found in DB"));
        return disciplineMapper.fromEntityToDto(entity);
    }

    @Override
    public List<DisciplineDTO> getByName(String name) throws NotFoundException {
        if(!disciplineRepository.existsDisciplineEntitiesByName(name))
            throw new NotFoundException("Disciplina non trovata");
        return disciplineMapper.fromEntityListToDtoList(disciplineRepository.findDisciplineEntityByName(name));
    }

    @Override
    public List<DisciplineDTO> getAll() {
        return disciplineMapper.fromEntityListToDtoList(disciplineRepository.findAll());
    }

    @Override
    public DisciplineDTO update(DisciplineDTO dto) throws NotFoundException {
        if(!disciplineRepository.existsById(dto.getId()))
            throw new NotFoundException("Disciplina non trovata");
        return disciplineMapper.fromEntityToDto(disciplineRepository.save(disciplineMapper.fromDtoToEntity(dto)));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!disciplineRepository.existsById(id))
            throw new NotFoundException("Disciplina non presente in DB");
        disciplineRepository.deleteById(id);
    }
}
