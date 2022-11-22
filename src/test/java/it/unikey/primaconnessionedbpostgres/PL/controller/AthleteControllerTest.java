package it.unikey.primaconnessionedbpostgres.PL.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;
import it.unikey.primaconnessionedbpostgres.BLL.service.abstracts.AthleteService;
import it.unikey.primaconnessionedbpostgres.PL.rest.AthleteRest;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static it.unikey.primaconnessionedbpostgres.utils.JsonUtil.asJsonString;
import static org.junit.jupiter.api.Assertions.*;

//UTILIZZA JUNIT 5 INVECE CHE 4 (QUELLO DI DEFAULT DI SPRING BOOT STARTER TEST)
@ExtendWith(SpringExtension.class)
//DICE A SPRING BOOT CHE QUESTA è UNA CLASSE DI TEST
@SpringBootTest
//SERVE PER FARE DI (DEPENDENCY INJECTION) ED AUTOCONFIGURARE IL MOCKMVC
@AutoConfigureMockMvc
public class AthleteControllerTest {
    @MockBean
    private AthleteService service;
    @Autowired
    private MockMvc mockMvc;

    //FONDAMENTALE
    @Test
    //OPZIONALE
    @DisplayName("GET /athlete - Full List")
    void testGetAllAthletes() throws Exception {
        //Setup our mocked service
        //LISTA MOCKATA
        List<AthleteDTO> mockAthleteList = List.of(
                new AthleteDTO(1, "Atleta", "Mockato", 25, 190.0, null),
                new AthleteDTO(2, "Atleta", "Mockato 2", 26, 175.0, null),
                new AthleteDTO(3, "Atleta", "Mockato 3", 27, 170.0, null));
        //TORNAMI LA LISTA MOCKATA DI ATLETI QUANDO CHIAMO LA GETALL DEL SERVICE
        doReturn(mockAthleteList).when(service).getAll();

        //Execute the GET request
        MvcResult result = mockMvc.perform(get("/athlete"))

                //Validate the response code and content type, then return as MvcResult
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Needed to convert the MvcResult in Java Object
        ObjectMapper mapper = new ObjectMapper();

        // this uses a TypeReference to inform Jackson about the Lists's generic type
        //MAGIA NERA, NON SERVE SAPERE COME FACCIA A FARE QUESTO "MAPPING"
        List<AthleteDTO> actualAthleteList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        //Validate the returned objects
        assertEquals(mockAthleteList, actualAthleteList, "The lists should be the same");
    }

    @Test
    @DisplayName("GET /athlete/1 - Found")
    void testGetAthleteByIdFound() throws Exception {
        //Setup our mocked service
        AthleteDTO mockAthlete = new AthleteDTO(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(mockAthlete).when(service).getById(1);

        //Execute the GET request
        mockMvc.perform(get("/athlete/{id}", 1))

                //Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Atleta")))
                .andExpect(jsonPath("$.lastName", is("Mockato")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.height", is(190.0)))
                .andExpect(jsonPath("$.discipline", is(IsNull.nullValue())));
    }

    @Test
    @DisplayName("GET /athlete/1 - Not Found")
    void testGetAthleteByNotFound() throws Exception {
        //Set up our mocked service
        when(service.getById(1)).thenThrow(new NotFoundException("Entity not found in DB"));

        //Execute the GET request
        mockMvc.perform(get("/athlete/{id}", 1))

                //Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /athlete - Success")
    void testCreateAthleteSuccess() throws Exception {
        //Setup our mocked service
        AthleteRest postAthlete = new AthleteRest(0, "Atleta", "Mockato", 25, 190.0, null);
        AthleteDTO mockAthlete = new AthleteDTO(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(mockAthlete).when(service).insert(any());

        //Execute the POST request
        mockMvc.perform(post("/athlete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postAthlete)))//visto l'oggetto passato viene convertito in JSON, non importa che sia un Rest o un DTO
                                                    //A PATTO CHE Rest e DTO abbiano le stesse proprietà (siano 2 POJO identici)

        //Validate the response code and content type
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //Validate the returned fields
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Atleta")))
        .andExpect(jsonPath("$.lastName", is("Mockato")))
        .andExpect(jsonPath("$.age", is(25)))
        .andExpect(jsonPath("$.height", is(190.0)))
        .andExpect(jsonPath("$.discipline", is(IsNull.nullValue())));
    }

    @Test
    @DisplayName("PUT /athlete/1 - Success")
    void testUpdateAthleteSuccess() throws Exception {
        //Setup our mocked service
        AthleteRest putAthlete = new AthleteRest(0, "Atleta", "Mockato", 25, 190.0, null);
        AthleteDTO mockAthlete = new AthleteDTO(1, "Atleta", "Mockato", 25, 190.0, null);
        doReturn(mockAthlete).when(service).update(any());

        //Execute the PUT request
        mockMvc.perform(put("/athlete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putAthlete)))//visto l'oggetto passato viene convertito in JSON, non importa che sia un Rest o un DTO
                                                           //A PATTO CHE Rest e DTO abbiano le stesse proprietà (siano 2 POJO identici)

        //Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //Validate the returned fields
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Atleta")))
        .andExpect(jsonPath("$.lastName", is("Mockato")))
        .andExpect(jsonPath("$.age", is(25)))
        .andExpect(jsonPath("$.height", is(190.0)))
        .andExpect(jsonPath("$.discipline", is(IsNull.nullValue())));
    }

    @Test
    @DisplayName("PUT /athlete/1 - Not Found")
    void testUpdateAthleteNotFound() throws Exception {
        //Setup our mocked service
        AthleteDTO putAthlete = new AthleteDTO(1, "Atleta", "Mockato", 25, 190.0, null);
        when(service.update(putAthlete)).thenThrow(new NotFoundException("Entity not found in DB"));

        //Execute the PUT request
        mockMvc.perform(put("/athlete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putAthlete)))//visto l'oggetto passato viene convertito in JSON, non importa che sia un Rest o un DTO
                                                   //A PATTO CHE Rest e DTO abbiano le stesse proprietà (siano 2 POJO identici)

                //Validate the response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /athlete/1 - Success")
    void testDeleteAthleteSuccess() throws Exception {
        //Setup our mocked service
        doNothing().when(service).delete(1);

        //Execute the DELETE request
        mockMvc.perform(delete("/athlete/{id}", 1))

        //
        .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /athlete/1 - Not Found")
    void testDeleteAthleteNotFound() throws Exception {
        //Setup our mocked service
        doThrow(new NotFoundException(("Entity not found in DB"))).when(service).delete(1);

        //Execute the DELETE request
        mockMvc.perform(delete("/athlete/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
