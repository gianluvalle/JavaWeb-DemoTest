package it.unikey.primaconnessionedbpostgres.DAL.repository;

import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AthleteRepository extends JpaRepository<AthleteEntity, Integer> {

    //Esempio di query usando keyword comprensibili a JPA
    List<AthleteEntity> findAthleteEntityByNameAndLastName(String name, String lastName);

    boolean existsAthleteEntitiesByNameAndLastName(String name, String lastName);

    //Esempio di query usando il linguaggio HQL
    @Query("SELECT a FROM Athletes a WHERE a.age < :ageToCompare")
    Set<AthleteEntity> trovaTramiteEtaSotto(Integer ageToCompare);

    //Esempio stessa query precedente usando keyword
    Set<AthleteEntity> findAthleteEntityByAgeBefore(Integer ageToCompare);

    Set<AthleteEntity> findAthleteEntityByDisciplineName(String disciplineName);

}
