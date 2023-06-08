package com.a.peliculas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.a.peliculas.entity.Pelicula;
import com.a.peliculas.service.IPeliculaService;

@Controller
public class PeliculasController {

	private IPeliculaService peliculaService;
	
	public PeliculasController(IPeliculaService peliculaService) {
		this.peliculaService = peliculaService;
	}
	
	@GetMapping("pelicula")
	public String crear(Model model) {
		
		Pelicula pelicula = new Pelicula();
		
		model.addAttribute("titulo", "Nueva pelicula");
		model.addAttribute("pelicula", pelicula);
		
		return "pelicula";
	}
	
	@GetMapping("pelicula/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model) {
		
		Pelicula pelicula = new Pelicula();
		
		model.addAttribute("titulo", "Editar pelicula");
		model.addAttribute("pelicula", pelicula);
		
		return "pelicula";
		
	}
}
