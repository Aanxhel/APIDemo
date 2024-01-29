package com.develop.pokemon.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.pokemon.app.models.entity.Pokemon;
import com.develop.pokemon.app.models.service.PokedexService;


@RestController
@CrossOrigin(origins = "http://localhost:4200" )
public class pkmController {

	@Autowired
	PokedexService pkmService;
	
	@GetMapping("/listapkm")
	public List<Pokemon> index(){
		
		List<Pokemon> listPmk = null;
		listPmk = pkmService.findAll();
		
		return listPmk;
	}
	
	@GetMapping("/listapkm/{id}")
	public ResponseEntity<Pokemon> getPkmId(@PathVariable Long id) {
		Pokemon pkm =  null;
		
		try {
			
			pkm =  pkmService.getPkmId(id);
			return new ResponseEntity<Pokemon>(pkm,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/inserdata")
	public void savePkm(@RequestBody Pokemon pokemon) {
		pkmService.savePkm(pokemon);
	}
	
	@PutMapping("/putpkm/{id}")
	public ResponseEntity<?> updatePkm(@RequestBody Pokemon pkm,@PathVariable Long id){
		
		Pokemon pkmExist = null;
		try {
			 pkmExist = pkmService.getPkmId(id);
			 
			 pkmExist.setNombre(pkm.getNombre());
			 pkmExist.setTipo(pkm.getTipo());
			 pkmExist.setGeneracion(pkm.getGeneracion());
			 pkmExist.setGenero(pkm.getGenero());
			 
			 
			pkmService.savePkm(pkmExist);
			return new ResponseEntity<Pokemon>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);

		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePkm(@PathVariable Long id) {
		pkmService.deletePkm(id);
	}
}
