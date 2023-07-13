package com.a.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.a.peliculas.entity.Actor;

public interface IActorRepository extends CrudRepository<Actor, Long> {

}
