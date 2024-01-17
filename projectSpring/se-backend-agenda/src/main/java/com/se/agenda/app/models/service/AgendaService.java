package com.se.agenda.app.models.service;

import java.util.List;

import com.se.agenda.app.models.bean.entity.Agenda;

public interface AgendaService {

	// lista
	public List<Agenda> findAll();
	
	// guarda
	public void guardarAgenda(Agenda agenda);
	
	// busqueda por id
	public Agenda obtenerAgendaId(Long id);
	
	//borrado de agenda
	public void borrarDatoAgenda(Long id);
}
