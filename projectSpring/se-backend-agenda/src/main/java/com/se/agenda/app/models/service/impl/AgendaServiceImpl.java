package com.se.agenda.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.agenda.app.models.bean.entity.Agenda;
import com.se.agenda.app.models.dao.AgendaDao;
import com.se.agenda.app.models.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService{
	
	@Autowired
	AgendaDao agendaDao;
	
	// buscar pkm
	public List<Agenda> findAll() {

		List<Agenda> lstAgenda = null;
		lstAgenda = (List<Agenda>) agendaDao.findAll();

		return lstAgenda;
	}

	// guardar
	public void guardarAgenda(Agenda agenda) {
		agendaDao.save(agenda);
	}

	// buscar por ID
	public Agenda obtenerAgendaId(Long id) {
		return agendaDao.findById(id).get();
	}

	// eliminar
	public void borrarDatoAgenda(Long id) {
		agendaDao.deleteById(id);
	}

}
