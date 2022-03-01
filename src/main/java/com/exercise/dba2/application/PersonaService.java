package com.exercise.dba2.application;

import com.exercise.dba2.domain.Persona;
import com.exercise.dba2.infrastructure.JdbcPersonaRepo;
import com.exercise.dba2.infrastructure.PersonaInputDTO;
import com.exercise.dba2.infrastructure.PersonaListaOutputDTO;
import com.exercise.dba2.infrastructure.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {

    @Autowired
    JdbcPersonaRepo personaRepo;

    @Override
    public PersonaListaOutputDTO getAllPersona() {
        Iterable<Persona> listaPersonas = personaRepo.findAll();
        List<PersonaOutputDTO> listaDTO = new ArrayList<>();
        for (Persona p:listaPersonas) listaDTO.add(this.toOutputDTO(p));
        return new PersonaListaOutputDTO(listaDTO.size(),listaDTO);
    }

    @Override
    public PersonaOutputDTO getPersonaById(String id) throws Exception {
        return this.toOutputDTO(personaRepo.findById(id));
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {
        Persona persona = personaRepo.add(PersonaInputDTO.toPersona(personaInputDTO));
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws Exception {
        Persona persona = personaRepo.put(id,PersonaInputDTO.toPersona(personaInputDTO));
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(String id) throws Exception {
        Persona persona = personaRepo.del(id);
        return this.toOutputDTO(persona);
    }

    private PersonaOutputDTO toOutputDTO(Persona persona){
        return new PersonaOutputDTO(
                persona.getId_persona(),
                persona.getUser(),
                persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.getActive(),
                persona.getCreated_date(),
                persona.getImage_url(),
                persona.getTermination_date()
        );
    }
}
