package com.ikon.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.service.TestInjectionService;
import com.ikon.process.service.impl.validaTokenRestImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IkonProcessApplicationTests {

	private static final Logger LOGG = LoggerFactory.getLogger(IkonProcessApplicationTests.class);
	
	@Autowired
	private TestInjectionService testInjectionService;
	
	@Autowired
	private validaTokenRestImpl componentes;
	
	@Test
	public void contextLoads() {
		assertNotNull(testInjectionService);
	}
	
//	@Test
	public void TestInjaction () {
		String valorTest = null;
		valorTest = this.testInjectionService.testInjection("castleipsra@gmail.com");
		LOGG.info("[::::::::: {} ::::::::]", valorTest);
		assertNotNull(valorTest);
	}
	
	//@Test
	public void validaToken () {
		Boolean tokenValido = false;
		String token = null;
		token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXN0bGVpcHNyYTFAZ21haWwuY29tIiwiZXhwIjoxNjI3MjI1MjcwLCJpYXQiOjE2MjcyMjUyMjAsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV19.lvteOORvwkgQiQjYrpnwvVaKdLl4kGKnsYdRd3u6Cmg";
		token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXN0bGVpcHNyYTFAZ21haWwuY29tIiwiZXhwIjoxNjI3MjI2OTc5LCJpYXQiOjE2MjcyMjY5MjksImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV19.RwvU934V-8FX7wWqoZxIhre2RMkj4rhX1pEonqP_oHE";
		token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXN0bGVpcHNyYTFAZ21haWwuY29tIiwiZXhwIjoxNjI3MjY1MDI1LCJpYXQiOjE2MjcyNjQ0MjUsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV19.qPNoJUV-V3bk_awryL2ur_6PnVGDADqGBHcExB2ODoA";
		
		tokenValido = componentes.obtieneTokenValida(token, "{}");
		
		assertEquals(true, tokenValido);
		
	}
	
	

}
