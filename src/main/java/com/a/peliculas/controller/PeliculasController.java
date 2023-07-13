package com.a.peliculas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.a.peliculas.entity.Pelicula;
import com.a.peliculas.service.IActorService;
import com.a.peliculas.service.IArchivoService;
import com.a.peliculas.service.IGeneroService;
import com.a.peliculas.service.IPeliculaService;

import jakarta.validation.Valid;

@Controller
public class PeliculasController {

	private IPeliculaService peliculaService;
	private IGeneroService generoService;
	private IActorService actorService;
	private IArchivoService archivoService;
	
	public PeliculasController(IPeliculaService peliculaService, IGeneroService generoService, IActorService actorService, IArchivoService archivoService) {
		this.peliculaService = peliculaService;
		this.generoService = generoService;
		this.actorService = actorService;
		this.archivoService = archivoService;
	}
	
	@GetMapping("/pelicula")
	public String crear(Model model) {
		
		Pelicula pelicula = new Pelicula();
		
		model.addAttribute("titulo", "Nueva pelicula");
		model.addAttribute("actores", actorService.findAll());
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("pelicula", pelicula);
		
		return "pelicula";
	}
	
	@GetMapping("/pelicula/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model) {
		
		Pelicula pelicula = new Pelicula();
		
		model.addAttribute("titulo", "Editar pelicula");
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("actores", actorService.findAll());
		
		return "pelicula";
		
	}
	
	@PostMapping("/pelicula")
	public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name = "ids") String ids, @RequestParam("archivo") MultipartFile file) {
		
		if(br.hasErrors()) {
			return "pelicula";
		}
		
		if(!file.isEmpty()) {
			String archivo = pelicula.getNombre() + getExtension(file.getOriginalFilename());
			pelicula.setImagen(archivo);
			try {
				archivoService.guardar(archivo, file.getInputStream());
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			pelicula.setImagen("default.jpg");
		}
		
		List<Long> idsActores = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
		
		pelicula.setProtagonistas(actorService.findAllById(idsActores));
		
		peliculaService.save(pelicula);
		
		return "redirect:home";
	}
	
	@GetMapping({"", "/home", "/index"})
	public String home(Model model) {
		
		model.addAttribute("peliculas", peliculaService.findAll());
		model.addAttribute("msg", "Catálogo actualizado");
		model.addAttribute("tipoMsg", "danger");
		
		return "home";
	}
	
	private String getExtension(String archivo) {
		return archivo.substring(archivo.lastIndexOf("."));
	}
}
