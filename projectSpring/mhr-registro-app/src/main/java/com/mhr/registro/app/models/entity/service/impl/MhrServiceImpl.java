package com.mhr.registro.app.models.entity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhr.registro.app.models.entity.Monster;
import com.mhr.registro.app.models.entity.dao.MhrDao;
import com.mhr.registro.app.models.entity.service.MhrService;

@Service
public class MhrServiceImpl implements MhrService {

	@Autowired
	MhrDao mhDao;

	// buscar 
	public List<Monster> buscarMh() {

		List<Monster> listPmk = null;
		listPmk = (List<Monster>) mhDao.findAll();

		return listPmk;
	}

	// guardar
	public void agragarMh(Monster mh) {
		mhDao.save(mh);
	}

	// buscar por ID
	public Monster obtenerMhId(Long id) {
		return mhDao.findById(id).get();
	}

	// eliminar
	public void deleteMh(Long id) {
		mhDao.deleteById(id);
	}
}
