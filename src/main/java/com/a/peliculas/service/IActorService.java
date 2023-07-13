package com.a.peliculas.service;

import java.util.List;

import com.a.peliculas.entity.Actor;

public interface IActorService {

	public List<Actor> findAll();
	public List<Actor> findAllById(List<Long> ids);
}
