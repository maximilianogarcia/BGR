package ar.com.bgr.serrano.integration.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.service.PaqueteService;
import ar.com.bgr.serrano.service.PresentacionService;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class PaqueteServiceTest {


	@Autowired
	PaqueteService service;
	
	@Autowired
	PresentacionService presentacionService;
	
	@Test
	public void testGetDisponibles(){
		List<PaqueteFromQuery> result = service.getPaquetesByProducto(2);
		assertNotNull(result);		
	}	
	
	@Test
	public void testGetById(){
		Paquete result = service.getById(2);
		assertNotNull(result);		
	}

	@Test
	public void testList() {
		System.out.println(service.list());
	}
	
	@Test
	public void testDisponibles() {
		System.out.println(service.getPaquetesByProducto(2));
	}

	@Test
	public void testStocks(){
		List<Object[]> list = presentacionService.getStocks();
		assertNotNull(list);
	}
}
