package it.unikey.primaconnessionedbpostgres.DAL.repository;

import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
// This SQL script will run before every test in this class
@Sql(scripts = {"/data.sql"})
public class AthleteRepositoryTest {

    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
//            .withDatabaseName("testcontainer")
//            .withUsername("test")
//            .withPassword("test");

    // > Spring Boot 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private AthleteRepository repository;

    @Test
    @DisplayName("Test findAll()")
    void testFindAll() {
        //Create mock list of Athletes
        List<AthleteEntity> expectedAthleteList = List.of(
            new AthleteEntity(1, "Edoardo", "Carradori", 25, 190.0, null)
        );

        //Retrieve All Athletes
        List<AthleteEntity> actualAthleteList = repository.findAll();

        //Assertions
        assertEquals(expectedAthleteList, actualAthleteList, "Should be found a list equal to the mocked one");
    }

    @Test
    @DisplayName("Test findById - Success")
    void testFindByIdSuccess() {
        //Create mock Athlete
        AthleteEntity expectedAthlete =  new AthleteEntity(1, "Edoardo", "Carradori", 25, 190.0, null);

        //Retrieve Athlete 1
        Optional<AthleteEntity> actualAthlete = repository.findById(1);

        //Assertions
        assertTrue(actualAthlete.isPresent(), "Should be found an athlete with ID 1");
        actualAthlete.ifPresent(athleteEntity -> assertEquals(expectedAthlete, actualAthlete.get(), "Should be equals"));
    }

    @Test
    @DisplayName("Test findById - Not Found")
    void testFindByIdNotFound() {
        //Retrieve Athlete 99 (expected not found)
        Optional<AthleteEntity> expectedAthlete = repository.findById(99);

        //Assertions
        assertFalse(expectedAthlete.isPresent(), "Should not be found");
    }

    @Test
    @DisplayName("Test save")
    void testSave() {
        //Create a test Athlete
        AthleteEntity newAthlete =  new AthleteEntity(0, "Francesco", "Di Rosa", 28, 183.0, null);

        //Persist the Athlete
        AthleteEntity savedAthlete = repository.save(newAthlete);

        //Retrieve the persisted Athlete
        Optional<AthleteEntity> actualAthlete = repository.findById(savedAthlete.getId());

        //Validations
        assertTrue(actualAthlete.isPresent(), "Should be present");
        actualAthlete.ifPresent(athleteEntity -> assertEquals(savedAthlete, actualAthlete.get(), "Should be equals"));
    }

    @Test
    @DisplayName("Test update")
    void testUpdate() {
        //Create a test Athlete
        AthleteEntity newAthlete =  new AthleteEntity(1, "Edoardo", "Corradori", 52, 138.0, null);

        //Update the Athlete
        AthleteEntity savedAthlete = repository.save(newAthlete);

        //Retrieve the persisted Athlete
        Optional<AthleteEntity> actualAthlete = repository.findById(savedAthlete.getId());

        //Validations
        assertTrue(actualAthlete.isPresent(), "Should be present");
        actualAthlete.ifPresent(athleteEntity -> assertEquals(savedAthlete, actualAthlete.get(), "Should be equals"));
    }

    @Test
    @DisplayName("Test delete")
    void testDelete() {
        //Delete Athlete 1
        repository.deleteById(1);

        //Retrieve Athlete 1 (expected not found)
        Optional<AthleteEntity> actualAthlete = repository.findById(1);

        //Assertions
        assertFalse(actualAthlete.isPresent(), "Should not be found");
    }

}
