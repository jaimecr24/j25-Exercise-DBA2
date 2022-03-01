package com.exercise.dba2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable {

    @Id
    private String id_persona;

    @Size(min=6,max=10)
    @NotBlank(message = "user is null")
    private String user;

    @NotBlank(message = "password is null")
    private String password;

    @NotBlank(message = "name is null")
    private String name;

    private String surname;

    @NotBlank(message = "company_email is null")
    private String company_email;

    @NotBlank(message = "personal_email is null")
    private String personal_email;

    @NotBlank(message = "city is null")
    private String city;

    @NotBlank(message = "active is null") // Clase Boolean sí puede ser nulo
    private Boolean active;

    @NotBlank(message = "created_date is null")
    private Date created_date;

    private String image_url;

    private Date termination_date;
}
