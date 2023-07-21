package com.a.peliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.a.peliculas.entity.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {

}
