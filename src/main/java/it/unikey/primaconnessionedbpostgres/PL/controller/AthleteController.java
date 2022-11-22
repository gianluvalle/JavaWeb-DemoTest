package it.unikey.primaconnessionedbpostgres.PL.controller;

import it.unikey.primaconnessionedbpostgres.BLL.Dto.AthleteDTO;
import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;
import it.unikey.primaconnessionedbpostgres.BLL.service.abstracts.AthleteService;
import it.unikey.primaconnessionedbpostgres.PL.mapper.impl.AthleteRestMapper;
import it.unikey.primaconnessionedbpostgres.PL.rest.AthleteRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/athlete")
public class AthleteController {

    private final AthleteService athleteService;
    private final AthleteRestMapper athleteRestMapper;

    //Con il verbo Get indichiamo la ricezione di dati dal Database
    @GetMapping
    private ResponseEntity<List<AthleteRest>> getAllAthletes(){
        List<AthleteDTO> athletes = athleteService.getAll();
        List<AthleteRest> athleteRests = athleteRestMapper.fromDtoListToRestList(athletes);
        return new ResponseEntity<>(athleteRests, HttpStatus.OK);
    }


    //Andando a specificare un path con le {} indichiamo la presenza di un pathvariable, che possiamo passare poi come
    //parametro nel metodo --> in questo caso {id}
    @GetMapping(path = "/{id}")
    private ResponseEntity<AthleteRest> getAthleteById(@PathVariable Integer id){
        try {
            AthleteDTO athlete = athleteService.getById(id);
            AthleteRest athleteRest = athleteRestMapper.fromDtoToRest(athlete);
            return new ResponseEntity<>(athleteRest, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{name}/{lastName}")
    private ResponseEntity<List<AthleteRest>> getByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName){
        try {
            List<AthleteDTO> dtoList = athleteService.getByNameAndLastName(name, lastName);
            List<AthleteRest> athleteRests = athleteRestMapper.fromDtoListToRestList(dtoList);
            return new ResponseEntity<>(athleteRests, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Stesso metodo precedente ma con utilizzo di QueryParam invece che PathVariable, il query Param a differenza del
    //path variable prevede coding ed encoding, quindi può prevedere degli spazi a differenza del path variable
    //esempio di sintassi di chiamata -> http://localhost:8080/athlete/fullname?name=Romelu&lastName=Lukaku
    @GetMapping(path = "/fullname")
    private ResponseEntity<List<AthleteRest>> getByFullName(
           @RequestParam("name") String name,
           @RequestParam("lastName") String lastName
    ){
        try {
            List<AthleteDTO> dtoList = athleteService.getByNameAndLastName(name, lastName);
            List<AthleteRest> athleteRests = athleteRestMapper.fromDtoListToRestList(dtoList);
            return new ResponseEntity<>(athleteRests, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Il vero Post indica un inserimento di dati nel database, dobbiamo fornire un Body nella richiesta e l'annotations
    //@RequestBody server per convertire il Json che arriva in una classe comprensibile a Java, i campi nel Json devono
    //corrispondere ai campi che abbiamo definito nella classe java (in questo caso la classe AthleteRest)
    @PostMapping
    private ResponseEntity<AthleteRest> postAthlete(@RequestBody AthleteRest athlete){
        AthleteDTO athleteDto = athleteService.insert(athleteRestMapper.fromRestToDto(athlete));
        AthleteRest athleteRest = athleteRestMapper.fromDtoToRest(athleteDto);
        return new ResponseEntity<>(athleteRest, HttpStatus.CREATED);
    }

    //Il verbo Put indica un update dell'entità nel database, a differenza della Post dobbiamo gestire l'eccezzione
    //che abbiamo specificato nel service
    @PutMapping
    private ResponseEntity<AthleteRest> putAthlete(@RequestBody AthleteRest athlete){
        try {
            AthleteDTO athleteDTO = athleteService.update(athleteRestMapper.fromRestToDto(athlete));
            AthleteRest athleteRest = athleteRestMapper.fromDtoToRest(athleteDTO);
            return new ResponseEntity<>(athleteRest, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Con il verbo Delete indichiamo l'eliminazione di un'entità nel database
    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Void> deleteAthlete(@PathVariable Integer id){
        try {
            athleteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
