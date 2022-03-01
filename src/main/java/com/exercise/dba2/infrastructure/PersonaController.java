package com.exercise.dba2.infrastructure;

import com.exercise.dba2.application.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {

    @Autowired
    IPersona personaService;

    @GetMapping
    public ResponseEntity<PersonaListaOutputDTO> findAll()
    {
        return new ResponseEntity<>(personaService.getAllPersona(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> findById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> add(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.addPersona(personaInputDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> put(
            @PathVariable String id,
            @RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.putPersona(id,personaInputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> del(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.delPersona(id), HttpStatus.OK);
    }
}
