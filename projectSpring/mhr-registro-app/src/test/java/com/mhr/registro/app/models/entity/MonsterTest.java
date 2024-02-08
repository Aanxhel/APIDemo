package com.mhr.registro.app.models.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MonsterTest {

	@Test
	void testNombreMhr() {
		Monster request = new Monster();
		request.setNombre("Rajan");
		String rqtEsperado = "Rajan";
		String rqtReal = request.getNombre();
		assertEquals(rqtEsperado, rqtReal);
//		assertTrue(rqtReal.equals("hola"));

	}

}
