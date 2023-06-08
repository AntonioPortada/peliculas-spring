package com.a.peliculas.service;

import java.util.List;

import com.a.peliculas.entity.Pelicula;

public interface IPeliculaService {

	public void save(Pelicula pelicula);
	public Pelicula findById(Long id);
	public List<Pelicula> findAll();
	public void delete(Long id);
}
