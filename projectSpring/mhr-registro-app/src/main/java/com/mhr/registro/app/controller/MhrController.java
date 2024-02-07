package com.mhr.registro.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhr.registro.app.models.entity.Monster;
import com.mhr.registro.app.models.entity.service.MhrService;

@RestController
public class MhrController {

	@Autowired
	MhrService mhrService;

	@GetMapping("/hello")
	public String hello() {
		return String.format("Hello World!");
	}

	@GetMapping("/lista")
	public List<Monster> listaMh() {
		List<Monster> lstMh = null;
		lstMh = mhrService.buscarMh();

		return lstMh;
	}

}
