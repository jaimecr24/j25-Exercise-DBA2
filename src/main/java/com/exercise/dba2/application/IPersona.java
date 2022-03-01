package com.exercise.dba2.application;

import com.exercise.dba2.infrastructure.PersonaInputDTO;
import com.exercise.dba2.infrastructure.PersonaListaOutputDTO;
import com.exercise.dba2.infrastructure.PersonaOutputDTO;

public interface IPersona {
    PersonaListaOutputDTO getAllPersona();
    PersonaOutputDTO getPersonaById(String id) throws Exception;
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(String id) throws Exception;
}
