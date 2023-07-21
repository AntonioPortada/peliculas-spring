package com.a.peliculas.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.a.peliculas.entity.Pelicula;

public interface IPeliculaService {

	public void save(Pelicula pelicula);
	public Pelicula findById(Long id);
	public List<Pelicula> findAll();
	public Page<Pelicula> findAll(Pageable pageable);
	public void delete(Long id);
}
