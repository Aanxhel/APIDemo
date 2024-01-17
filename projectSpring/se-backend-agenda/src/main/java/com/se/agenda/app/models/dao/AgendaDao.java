package com.se.agenda.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.se.agenda.app.models.bean.entity.Agenda;

public interface AgendaDao extends CrudRepository<Agenda, Long>{

}
