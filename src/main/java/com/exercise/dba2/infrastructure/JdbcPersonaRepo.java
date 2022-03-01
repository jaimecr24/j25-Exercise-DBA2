package com.exercise.dba2.infrastructure;

import com.exercise.dba2.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class JdbcPersonaRepo {

    @Autowired JdbcOperations jdbcOperations;
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public List<Persona> findAll(){
        try {
            return jdbcOperations.queryForObject("select * from persona", new personaListaRowMapper());
        } catch (EmptyResultDataAccessException e)
        {
            return new ArrayList<>();
        }
    }

    public Persona findById(String id) {
        return jdbcOperations.queryForObject("select * from persona where id_persona=?", new PersonaRowMapper(), id);
    }

    public Persona add(final Persona persona)
    {
        Date d = new Date();
        String newId = String.valueOf(idGenerator.getAndIncrement());
        final String sql = "insert into persona(id_persona,user,password,name,surname,company_email,personal_email,city,active,created_date,image_url,termination_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        persona.setId_persona(newId);
        jdbcOperations.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_PERSONA"});
                    ps.setString(1, persona.getId_persona());
                    ps.setString(2, persona.getUser());
                    ps.setString(3, persona.getPassword());
                    ps.setString(4, persona.getName());
                    ps.setString(5, persona.getSurname());
                    ps.setString(6, persona.getCompany_email());
                    ps.setString(7, persona.getPersonal_email());
                    ps.setString(8, persona.getCity());
                    ps.setBoolean(9, persona.getActive());
                    ps.setDate(10, new java.sql.Date(d.getTime()));
                    ps.setString(11, persona.getImage_url());
                    ps.setDate(12, new java.sql.Date(d.getTime()));
                    return ps;
                });
        return persona;
    }

    public Persona put(String id, Persona persona)
    {
        //Persona p = jdbcOperations.queryForObject("select * from persona where id_persona=?", new PersonaRowMapper(), id);
        String sql = "update persona set user=?,password=?,name=?,surname=?,company_email=?,personal_email=?,city=?,active=?,created_date=?,image_url=?,termination_date=? where id_persona=?";
        jdbcOperations.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_PERSONA"});
                    ps.setString(12, id);
                    ps.setString(1, persona.getUser());
                    ps.setString(2, persona.getPassword());
                    ps.setString(3, persona.getName());
                    ps.setString(4, persona.getSurname());
                    ps.setString(5, persona.getCompany_email());
                    ps.setString(6, persona.getPersonal_email());
                    ps.setString(7, persona.getCity());
                    ps.setBoolean(8, persona.getActive());
                    ps.setDate(9, (persona.getCreated_date()==null)
                            ? null
                            : new java.sql.Date(persona.getCreated_date().getTime()));
                    ps.setString(10, persona.getImage_url());
                    ps.setDate(11, (persona.getTermination_date()==null)
                            ? null
                            : new java.sql.Date(persona.getTermination_date().getTime()));
                    return ps;
                });
        return jdbcOperations.queryForObject("select * from persona where id_persona=?", new PersonaRowMapper(), id);
    }

    public Persona del(String id)
    {
        Persona p = jdbcOperations.queryForObject("select * from persona where id_persona=?", new PersonaRowMapper(), id);
        String sql = "delete from persona where id_persona="+id;
        jdbcOperations.execute(sql);
        return p;
    }

    private class PersonaRowMapper implements RowMapper<Persona>
    {
        @Override
        public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Persona(
                    rs.getString("id_persona"),
                    rs.getString("user"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("company_email"),
                    rs.getString("personal_email"),
                    rs.getString("city"),
                    rs.getBoolean("active"),
                    rs.getDate("created_date"),
                    rs.getString("image_url"),
                    rs.getDate("termination_date"));
        }
    }

    private class personaListaRowMapper implements RowMapper<List<Persona>> {

        @Override
        public List<Persona> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArrayList<Persona> listaPersonas = new ArrayList<>();
            do
            {
                listaPersonas.add(new Persona(
                        rs.getString("id_persona"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("company_email"),
                        rs.getString("personal_email"),
                        rs.getString("city"),
                        rs.getBoolean("active"),
                        rs.getDate("created_date"),
                        rs.getString("image_url"),
                        rs.getDate("termination_date")));
            }
            while (rs.next());
            return listaPersonas;
        }
    }
}
