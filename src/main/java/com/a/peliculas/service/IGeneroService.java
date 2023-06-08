package com.a.peliculas.service;

import java.util.List;

import com.a.peliculas.entity.Genero;

public interface IGeneroService {

	public void save(Genero genero);
	public Genero findById(Long id);
	public void delete(Long id);
	public List<Genero> findAll();
}
