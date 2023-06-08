package com.a.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.a.peliculas.entity.Pelicula;

public interface IPeliculaRepository extends CrudRepository<Pelicula, Long> {

}
