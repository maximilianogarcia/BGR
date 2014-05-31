package ar.com.bgr.serrano.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.bgr.serrano.model.Calificativo;
import ar.com.bgr.serrano.service.CalificativoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class CalificativoServiceTest {

	
	@Autowired
	CalificativoService service;
	
	//@Test
	public void testSave() {
		Calificativo calificativo = new Calificativo();
		calificativo.setName("negocio grande");
		calificativo.setType("Proveedor");
		service.save(calificativo);
	}

	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testRemove(){
	}

}
