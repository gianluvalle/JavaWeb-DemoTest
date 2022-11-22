package it.unikey.primaconnessionedbpostgres.DAL.repository;

import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import it.unikey.primaconnessionedbpostgres.DAL.entity.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Integer> {

    List<DisciplineEntity> findDisciplineEntityByName(String nameToFind);

    boolean existsDisciplineEntitiesByName(String name);

    DisciplineEntity findDisciplineEntityByAthletesContains(AthleteEntity entity);

}
