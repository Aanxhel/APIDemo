package com.se.agenda.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.agenda.app.models.bean.entity.Agenda;
import com.se.agenda.app.models.service.AgendaService;

@RestController
public class AgendaController {
	
	@Autowired
	AgendaService agendaService;
	
	@GetMapping("/listaagenda")
	public List<Agenda> index(){
		
		List<Agenda> lstAgenda = null;
		lstAgenda = agendaService.findAll();
		
		return lstAgenda;
	}
	
}
