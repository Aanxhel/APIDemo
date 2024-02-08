package com.mhr.registro.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhr.registro.app.models.entity.Monster;
import com.mhr.registro.app.models.service.MhrService;

@RestController
public class MhrController {

	@Autowired
	MhrService mhrService;

	@GetMapping("/hello")
	public String hello() {
		return String.format("Hello World!");
	}

	// muestra de lista
	@GetMapping("/lista")
	public List<Monster> buscarMh() {
		List<Monster> lstMh = null;
		lstMh = mhrService.buscarMh();

		return lstMh;
	}

	// busqueda por id
	@GetMapping("/listamhr/{id}")
	public ResponseEntity<Monster> agragarMh(@PathVariable Long id) {
		Monster mhr = null;

		try {

			mhr = mhrService.obtenerMhId(id);
			return new ResponseEntity<Monster>(mhr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Monster>(HttpStatus.NOT_FOUND);
		}

	}

	// insertar postman
	@PostMapping("/inserdata")
	public void agragarMh(@RequestBody Monster request) {
		mhrService.agragarMh(request);
	}

	// actualizar
	@PutMapping("/updateMhr/{id}")
	public ResponseEntity<?> updateMhr(@RequestBody Monster request, @PathVariable Long id) {

		Monster mhr = null;
		try {
			mhr = mhrService.obtenerMhId(id);

			mhr.setNombre(mhr.getNombre());
			mhr.setVentaja(mhr.getVentaja());
			mhr.setDesventaja(mhr.getDesventaja());
			mhr.setDificultad(mhr.getDificultad());
			

			mhrService.agragarMh(mhr);

			return new ResponseEntity<Monster>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Monster>(HttpStatus.NOT_FOUND);

		}
	}

	// borrar
	@DeleteMapping("/delete/{id}")
	public void deleteMh(@PathVariable Long id) {
		mhrService.deleteMh(id);
	}

}
