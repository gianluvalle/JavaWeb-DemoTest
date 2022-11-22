package it.unikey.primaconnessionedbpostgres.BLL.service;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;
import it.unikey.primaconnessionedbpostgres.BLL.mapper.impl.AthleteMapper;
import it.unikey.primaconnessionedbpostgres.BLL.service.abstracts.AthleteService;
import it.unikey.primaconnessionedbpostgres.DAL.entity.AthleteEntity;
import it.unikey.primaconnessionedbpostgres.DAL.repository.AthleteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AthleteServiceImplTest {

    @Autowired
    private AthleteService service;

    @Autowired
    private AthleteMapper mapper;

    @MockBean
    private AthleteRepository repository;

    @Test
    @DisplayName("Test getById - Success")
    void testGetByIdSuccess() throws Exception {
        //Setup our mock
        AthleteEntity mockAthlete = new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(Optional.of(mockAthlete)).when(repository).findById(1);

        //Execute the service call
        AthleteDTO returnedAthlete = service.getById(1);

        //Assert the response
        assertEquals(returnedAthlete, mapper.fromEntityToDto(mockAthlete), "Athlete should be the same");
    }

    @Test
    @DisplayName("Test getById - Not Found")
    void testGetByIdNotFound() throws Exception {
        //Setup our mock
        doReturn(Optional.empty()).when(repository).findById(1);

        //Execute the service call
        Exception exception = assertThrows(NotFoundException.class, () -> service.getById(1));

        String expectedMessage = "Entity not found in DB";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test getAll")
    void testGetAll() throws Exception {
        //Setup our mock
        List<AthleteEntity> mockAthleteList = List.of(
            new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null),
            new AthleteEntity(2, "Atleta", "Mockato 2", 41, 179.0, null));
        doReturn(mockAthleteList).when(repository).findAll();

        //Execute the service call
        List<AthleteDTO> returnedAthleteList = service.getAll();

        //Assert the response
        assertEquals(returnedAthleteList, mapper.fromEntityListToDtoList(mockAthleteList), "Athletes should be the same");
    }

    @Test
    @DisplayName("Test insert Athlete")
    void testInsertAthlete() throws Exception {
        //Setup our mock
        AthleteEntity mockAthlete = new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(mockAthlete).when(repository).save(mockAthlete);

        //Execute the service call
        AthleteDTO returnedAthlete = service.insert(mapper.fromEntityToDto(mockAthlete));

        //Assert the response
        assertEquals(returnedAthlete, mapper.fromEntityToDto(mockAthlete), "Athlete should be the same");
    }

    @Test
    @DisplayName("Test update Athlete - Success")
    void testUpdateAthleteSuccess() throws Exception {
        //Setup our mocked service
        AthleteEntity mockAthlete = new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(true).when(repository).existsById(1);
        doReturn(mockAthlete).when(repository).save(mockAthlete);

        //Execute the service call
        AthleteDTO returnedAthlete = service.update(mapper.fromEntityToDto(mockAthlete));

        //Assert the response
        assertEquals(returnedAthlete, mapper.fromEntityToDto(mockAthlete), "Athlete should be the same");
    }

    @Test
    @DisplayName("Test update Athlete - Not Found")
    void testUpdateAthleteNotFound() throws Exception {
        //Setup our mocked service
        AthleteEntity mockAthlete =   new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(false).when(repository).existsById(1);

        //Execute the service call
        Exception exception = assertThrows(NotFoundException.class, () -> service.update(mapper.fromEntityToDto(mockAthlete)));

        String expectedMessage = "Entity not found in db";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test delete Athlete - Success")
    void testDeleteAthleteSuccess() throws Exception {
        //Setup our mocked service
        //AthleteEntity mockAthlete = new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(true).when(repository).existsById(1);
        doNothing().when(repository).deleteById(1);

        //Execute the service call

        //Assert the response
        assertDoesNotThrow(() -> service.delete(1));
    }

    @Test
    @DisplayName("Test delete Athlete - Not Found")
    void testDeleteAthleteNotFound() throws Exception {
        //Setup our mocked service
        //AthleteEntity mockAthlete = new AthleteEntity(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(false).when(repository).existsById(1);

        //Execute the service call
        Exception exception = assertThrows(NotFoundException.class, () -> service.delete(1));

        String expectedMessage = "Entity not found in db";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
