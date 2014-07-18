/**
 * 
 */
package ar.com.bgr.serrano.integration.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.service.UnidadDeMedidaService;

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
public class UnidadDeMedidaServiceTest {
	
	@Autowired
	UnidadDeMedidaService service;

	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testListNoDivisibles(){
		System.out.println(service.listNoDivisibles());
	}

}
